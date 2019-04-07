package com.sixplus.demo.bean;

import com.sixplus.demo.constant.CommonResponse;
import com.sixplus.demo.constant.HttpResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebResponse {
    private String code;
    private String message;
    private Object result;

    public void setCode(String code){this.code = code;}

    public void setMessage(String message){this.message = message;}

    public void setResult(Object result){this.result = result;}

    public WebResponse(HttpResponseStatusEnum httpResponseStatusEnum){
        this.code = httpResponseStatusEnum.getCode();
        this.message = httpResponseStatusEnum.getMessage();
    }

    public WebResponse(CommonResponse commonResponse){
        this.code = commonResponse.getCode();
        this.message = commonResponse.getMessage();
    }

    public static WebResponse success(){
        return new WebResponse(HttpResponseStatusEnum.SUCCESS.getCode(),HttpResponseStatusEnum.SUCCESS.getMessage(),null);
    }

    public static WebResponse success(Object result){
        return new WebResponse(HttpResponseStatusEnum.SUCCESS.getCode(),HttpResponseStatusEnum.SUCCESS.getMessage(),result);
    }

    public static WebResponse forbidden(){
        return new WebResponse(HttpResponseStatusEnum.FORBIDDEN.getCode(),HttpResponseStatusEnum.FORBIDDEN.getMessage(),null);
    }
}
