package com.sixplus.demo.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HttpResponseStatusEnum implements CommonResponse {
    SUCCESS("0","success"),FORBIDDEN("2","forbidden"),ERROR("3","error");

    private String code;
    private String message;

    @Override
    public String getCode(){return code;}

    @Override
    public String getMessage(){return message;}

    @Override
    public Object getResult(){return null;}
}
