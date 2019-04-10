package com.sixplus.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixplus.demo.constant.OrderStatusEnum;
import com.sixplus.demo.entity.Deal;
import com.sixplus.demo.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
public class DealController {
    @Autowired
    private DealRepository dealRepository;

    @GetMapping(value = "/deals")//管理员获取所有订单
    public List<Deal> getAllOrders()
    {
        return dealRepository.findAll();
    }

    @GetMapping(value = "/deals/{buyername}")//获取指定用户的订单
    public List<Deal> getOrdersByBuyerid(@PathVariable(value = "buyername") String buyername)
    {
//        List<Deal> deals = dealRepository.findByBuyername(buyername);
//        List<Deal> ret = new ArrayList<>();
//        for (Deal o : deals)
//        {
//            if(o.getStatusEnum().getCode().equals("0")){
//                ret.add(o);
//            }
//        }
        return dealRepository.findByBuyername(buyername);
    }

    @PostMapping(value = "/deals/{buyername}")//提交订单
    public void createOrders(@PathVariable(value = "buyername") String buyername,
                             @RequestParam(value = "goodid") String goodlists) throws Exception
    {
        List<Integer> goods = new ArrayList<>();
        for (int i=0;i<goodlists.length();i++)
        {
            StringBuilder sb = new StringBuilder();
            while (goodlists.charAt(i)!=',')
            {
                sb.append(goodlists.charAt(i));
            }
            goods.add(Integer.valueOf(sb.toString()));
        }
        System.out.println(goods);
        Date date = new Date();
        for(Integer goodid:goods){
            Deal deal = new Deal();
            deal.setOrderid(new Long(1));
            deal.setDate(date);
            deal.setBuyername(buyername);
            deal.setGoodid(goodid);
            deal.setRate(0);
            deal.setStatusEnum(OrderStatusEnum.Ordered);
            dealRepository.save(deal);
        }
    }
}
