package com.sixplus.demo.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatusEnum implements OrderInfo {
    Ordered("1","已下单"),Payed("2","已付款"),Sent("3","已发货"),Received("4","已签收"),Finished("0","已完成");

    private String code;
    private String message;

    @Override
    public String getCode(){return code;}
    @Override
    public String getMessage(){return message;}
}
