package com.sixplus.demo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sixplus.demo.entity.User;
import com.sixplus.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomJSONLoginFilter extends AbstractAuthenticationProcessingFilter {
    private UserService userService;

    CustomJSONLoginFilter(String defaultFilterProcessUrl,UserService userService){
        super(new AntPathRequestMatcher(defaultFilterProcessUrl, HttpMethod.POST.name()));
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        JSONObject requestBody = getRequestBody(httpServletRequest);
        String username = requestBody.getString("username");
        String password = requestBody.getString("password");
        validateUsernameAndPassword(username,password);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        String status = userService.getByUsername(username).getStatus();
        if(status.equals("2")){
            System.out.println(status);
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        else if(status.equals("1")){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        }
        return new UsernamePasswordAuthenticationToken(username,password,simpleGrantedAuthorities);
    }

    private JSONObject getRequestBody(HttpServletRequest request) throws AuthenticationException{
        try{
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = request.getInputStream();
            byte[] bs = new byte[StreamUtils.BUFFER_SIZE];
            int len;
            while ((len=inputStream.read(bs))!=-1){
                stringBuilder.append(new String(bs,0,len));
            }
            return JSON.parseObject(stringBuilder.toString());
        }catch (IOException e){
            logger.error("get request body error.");
        }
        throw new AuthenticationServiceException("invalid request body");
    }

    private void validateUsernameAndPassword(String username,String password) throws AuthenticationException{
        User user = userService.getByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user not exists");
        }
        if (!user.getPassword().equals(password)){
            throw new AuthenticationServiceException("error username or password");
        }
    }
}
