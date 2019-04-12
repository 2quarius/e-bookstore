package com.sixplus.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixplus.demo.bean.DealResponse;
import com.sixplus.demo.constant.OrderStatusEnum;
import com.sixplus.demo.entity.Deal;
import com.sixplus.demo.entity.Storage;
import com.sixplus.demo.repository.DealRepository;
import com.sixplus.demo.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.net.www.content.text.Generic;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
public class DealController {
    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private StorageRepository storageRepository;

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
            dr.setGoodname(storageRepository.getOne(d.getGoodid()).getName());
            dr.setRate(d.getRate());
            dr.setNumber(d.getNumber());
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
                case 2: sth.add((T)Long.valueOf(sb.toString()));break;
                case 3: sth.add((T)Double.valueOf(sb.toString()));break;
                default:
                    break;
            }
            sb.delete(0,sb.length()-1);
        }
        return sth;
    }

    @GetMapping(value = "/deals")//管理员获取所有订单
    public List<DealResponse> getAllOrders()
    {
        List<Deal> deals = dealRepository.findAll();
        return getDealResponses(deals);
    }

    @GetMapping(value = "/deals/{buyername}")//获取指定用户的订单
    public List<DealResponse> getOrdersByBuyerid(@PathVariable(value = "buyername") String buyername)
    {
       List<Deal> deals = dealRepository.findByBuyername(buyername);
        return getDealResponses(deals);
    }

    @PostMapping(value = "/deals/{buyername}")//提交订单
    public void createOrders(@PathVariable(value = "buyername") String buyername,
                             @RequestParam(value = "goodid",required = false) String goodlists,
                             @RequestParam(value = "goodnumber") String numberlists)
    {
        List<Integer> goods = getList(goodlists,good);
        List<Integer> numbers = getList(numberlists,good);
        if(goods.size()==numbers.size())
        {
            Date date = new Date();
            for(int i = 0;i<goods.size();i++){
                Deal deal = new Deal();
                deal.setOrderid(new Long(1));
                deal.setDate(date);
                deal.setBuyername(buyername);
                deal.setGoodid(goods.get(i));
                Storage storage = storageRepository.getOne(goods.get(i));
                storage.setStores(storage.getStores()-numbers.get(i));
                deal.setRate(new Double(0));
                deal.setStatusEnum(OrderStatusEnum.Ordered);
                deal.setNumber(numbers.get(i));
                dealRepository.save(deal);
            }
        }

    }

    @PostMapping(value = "/deals/rate")//用户评价订单
    public void rate(@RequestParam(value = "idlist") String idlist,
                     @RequestParam(value = "ratelist") String ratelist)
    {
        List<Long> ids = getList(idlist,id);
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
}
