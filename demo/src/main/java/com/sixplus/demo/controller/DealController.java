package com.sixplus.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixplus.demo.bean.DealResponse;
import com.sixplus.demo.bean.Sale;
import com.sixplus.demo.constant.OrderStatusEnum;
import com.sixplus.demo.entity.Deal;
import com.sixplus.demo.entity.Storage;
import com.sixplus.demo.repository.DealRepository;
import com.sixplus.demo.repository.StorageRepository;
import com.sixplus.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
public class DealController {
    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private StorageRepository storageRepository;

    private String REDIS_KEY_PREFIX_ORDER_ID = new String("portal:orderId:");
    @Autowired
    private RedisService redisService;

    private final Integer good = new Integer(1);

    private final Integer id = new Integer(2);

    private final Integer rate = new Integer(3);

    private List<DealResponse> getDealResponses(List<Deal> deals)
    {
        List<DealResponse> response = new ArrayList<>();
        for (Deal d:deals)
        {
            DealResponse dr = new DealResponse();
            dr.setId(d.getOrderid());
            dr.setDate(DateFormat.getDateTimeInstance(2,2, Locale.CHINESE).format(d.getDate()));
            dr.setRate(d.getRate());
            Map<String,Integer> drntn = new HashMap<>();
            Map<Integer,Integer> ditn = d.getGoodidToNumber();
            for (Map.Entry<Integer,Integer> entry:ditn.entrySet())
            {
                drntn.put(storageRepository.getOne(entry.getKey()).getName(),entry.getValue());
            }
            dr.setGoodnameToNumber(drntn);
            response.add(dr);
        }
        return response;
    }

    private <T> List<T> getList(String lists,Integer spec)
    {
        List<T> sth = new ArrayList<>();
        for (int i=0;i<lists.length();i++)
        {
            StringBuilder sb = new StringBuilder();
            while (i<lists.length()&&lists.charAt(i)!=',')
            {
                sb.append(lists.charAt(i));
                i++;
            }
            switch (spec)
            {
                case 1: sth.add((T)Integer.valueOf(sb.toString()));break;
                case 2: sth.add((T)sb.toString());break;
                case 3: sth.add((T)Double.valueOf(sb.toString()));break;
                default:
                    break;
            }
            sb.delete(0,sb.length()-1);
        }
        return sth;
    }

    private Sale formSale(Deal d,Long keji,Long shenghuo,Long wenxue,char choose)
    {
        Map<Integer,Integer> map = d.getGoodidToNumber();
        for (Map.Entry<Integer,Integer> entry:map.entrySet())
        {
            if(storageRepository.getOne(entry.getKey()).getSubject().equals("科技"))
            {
                keji += entry.getValue().longValue();
            }
            else if(storageRepository.getOne(entry.getKey()).getSubject().equals("生活"))
            {
                shenghuo += entry.getValue().longValue();
            }
            else if(storageRepository.getOne(entry.getKey()).getSubject().equals("文学"))
            {
                wenxue += entry.getValue().longValue();
            }
        }
        Sale sale;
        String date = new SimpleDateFormat("yyyyMMdd").format(d.getDate());
        switch (choose)
        {
            case 'b':sale = new Sale(d.getBuyername(),keji,shenghuo,wenxue);break;
            case 't':sale = new Sale(date, keji, shenghuo, wenxue);break;
            default: sale = new Sale();
        }
        return sale;
    }
    @GetMapping(value = "/deals/admin/userSpecify")//管理员获取用户购买情况
    public List<Sale> getAllSaleByBuyname()
    {
        List<Deal> deals = dealRepository.findAll();
        List<Sale> result = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (Deal d:deals)
        {
            if (!names.contains(d.getBuyername()))
            {
                names.add(d.getBuyername());
                Sale sale = formSale(d,new Long(0),new Long(0),new Long(0),'b');
                result.add(sale);
            }
            else {
                for (int i = 0;i<result.size();i++)
                {
                    if (result.get(i).getDateOrBuyername().equals(d.getBuyername()))
                    {
                        Sale sale = result.get(i);
                        sale = formSale(d,sale.getSUBJECT_KEJI_SALES(),
                                sale.getSUBJECT_SHENGHUO_SALES(),sale.getSUBJECT_WENXUE_SALES(),'b');
                        result.set(i,sale);
                    }
                }
            }
        }
        return result;
    }
    @GetMapping(value = "/deals/user/statistic/{username}")//
    public List<Sale> getMySale(@PathVariable(value = "username") String username)
    {
        List<Deal> deals = dealRepository.findByBuyername(username);
        List<Sale> result = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        for (Deal d: deals) {
            String date = new SimpleDateFormat("yyyyMMdd").format(d.getDate());
            if (!dates.contains(date)) {
                dates.add(date);
                Sale sale = formSale(d,new Long(0),new Long(0),new Long(0),'t');
                result.add(sale);
            }
            else {
                for (int i = 0;i<result.size();i++)
                {
                    if (result.get(i).getDateOrBuyername().equals(date))
                    {
                        Sale sale = result.get(i);
                        sale = formSale(d,sale.getSUBJECT_KEJI_SALES(),
                                sale.getSUBJECT_SHENGHUO_SALES(),sale.getSUBJECT_WENXUE_SALES(),'t');
                        result.set(i,sale);
                    }
                }
            }
        }
        return result;
    }
    @GetMapping(value = "/deals/admin/statistic")//管理员销量统计
    public List<Sale> getAllSale()
    {
        List<Deal> deals = dealRepository.findAll();
        List<Sale> result = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        for (Deal d: deals) {
            String date = new SimpleDateFormat("yyyyMMdd").format(d.getDate());
            if (!dates.contains(date)) {
                dates.add(date);
                Sale sale = formSale(d,new Long(0),new Long(0),new Long(0),'t');
                result.add(sale);
            }
            else {
                for (int i = 0;i<result.size();i++)
                {
                    if (result.get(i).getDateOrBuyername().equals(date))
                    {
                        Sale sale = result.get(i);
                        sale = formSale(d,sale.getSUBJECT_KEJI_SALES(),
                                sale.getSUBJECT_SHENGHUO_SALES(),sale.getSUBJECT_WENXUE_SALES(),'t');
                        result.set(i,sale);
                    }
                }
            }
        }
        return result;
    }

    @GetMapping(value = "/deals")//管理员获取所有订单
    public List<DealResponse> getAllOrders()
    {
        List<Deal> deals = dealRepository.findAll();
        return getDealResponses(deals);
    }

    @GetMapping(value = "/deals/user/{buyername}")//获取指定用户的订单
    public List<DealResponse> getOrdersByBuyerid(@PathVariable(value = "buyername") String buyername)
    {
       List<Deal> deals = dealRepository.findByBuyername(buyername);
       return getDealResponses(deals);
    }

    @PostMapping(value = "/deals/user/{buyername}")//提交订单
    public void createOrders(@PathVariable(value = "buyername") String buyername,
                             @RequestParam(value = "goodid",required = false) String goodlists,
                             @RequestParam(value = "goodnumber") String numberlists)
    {
        List<Integer> goods = getList(goodlists,good);
        List<Integer> numbers = getList(numberlists,good);
        System.out.println(goods.size());
        System.out.println(numbers.size());
        if(goods.size()==numbers.size())
        {
            Date date = new Date();
            Deal deal = new Deal();
            deal.setDate(date);
            deal.setBuyername(buyername);
            Map<Integer,Integer> idtnum = new HashMap<>();
            for(int i = 0;i<goods.size();i++){
                idtnum.put(goods.get(i),numbers.get(i));
                Storage storage = storageRepository.getOne(goods.get(i));
                storage.setStores(storage.getStores()-numbers.get(i));
            }
            deal.setGoodidToNumber(idtnum);
            deal.setRate(new Double(0));
            deal.setStatusEnum(OrderStatusEnum.Ordered);
            deal.setOrderid(generateDealId(deal));
            dealRepository.save(deal);
        }

    }

    @PostMapping(value = "/deals/user/rate")//用户评价订单
    public void rate(@RequestParam(value = "idlist") String idlist,
                     @RequestParam(value = "ratelist") String ratelist)
    {
        List<String> ids = getList(idlist,id);
        List<Double> rates = getList(ratelist,rate);
        if(ids.size()==rates.size())
        {
            for(int i = 0;i<ids.size();i++)
            {
                Deal deal = dealRepository.getOne(ids.get(i));
                deal.setRate(rates.get(i));
                if(rates.get(i)!=0) {
                    deal.setStatusEnum(OrderStatusEnum.Finished);
                }
                dealRepository.save(deal);
            }
        }
    }
    /**
     * 生成14位订单编号:8位日期+6位以上自增id
     */
    private String generateDealId(Deal deal) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = REDIS_KEY_PREFIX_ORDER_ID + date;
        Long increment = redisService.increment(key, 1);
        sb.append(date);
        String incrementStr = increment.toString();
        if(incrementStr.length()<=6){
            sb.append(String.format("%06d",increment));
        }else{
            sb.append(incrementStr);
        }
        return sb.toString();
    }
}
