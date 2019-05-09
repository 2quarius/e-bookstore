package com.sixplus.demo.config.handlers;

import com.alibaba.fastjson.JSON;
import com.sixplus.demo.bean.WebResponse;
import com.sixplus.demo.entity.User;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.getAuthorities().toString());
        WebResponse response = new WebResponse();
        if(authentication.getAuthorities().toString().contains("ADMIN")){
            response = WebResponse.success("管理员登录成功");
//            addAdminCookie(getUserName(authentication),httpServletResponse);
        }
        else if(authentication.getAuthorities().toString().contains("USER")){
            response = WebResponse.success("登录成功");
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(response));
        System.out.println(httpServletResponse);
    }

    private String getUserName(Authentication authentication){
        return ((User) authentication.getPrincipal()).getUsername();
    }

    private void addAdminCookie(String user,HttpServletResponse response){
        Cookie adminCookie = getAdminCookie(user);
        response.addCookie(adminCookie);
    }

    private Cookie getAdminCookie(String user){
        Cookie adminCookie = new Cookie("admin",user);
        adminCookie.setMaxAge(60*60*1);
        System.out.println(adminCookie);
        return adminCookie;
    }
}
