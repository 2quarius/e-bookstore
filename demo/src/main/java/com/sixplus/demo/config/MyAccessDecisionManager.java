//package com.sixplus.demo.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Iterator;
//
//@Slf4j
//@Component
//public class MyAccessDecisionManager implements AccessDecisionManager {
//    @Override
//    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException{
//        if (configAttributes==null){
//            return;
//        }
//        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
//        while (iterator.hasNext()){
//            ConfigAttribute c = iterator.next();
//            String needAuth = c.getAttribute();
//            for (GrantedAuthority ga:authentication.getAuthorities()){
//                if (needAuth.trim().equals(ga.getAuthority())){
//                    return;
//                }
//            }
//        }
//        throw new AccessDeniedException("you dont have access");
//    }
//    @Override
//    public boolean support
//}
