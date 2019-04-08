package com.sixplus.demo.config;

import com.sixplus.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class MyUserDetails extends User implements UserDetails {
    public MyUserDetails(User user)
    {
        if(user!=null){
            System.out.println("not null");
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.setUsername(user.getUsername());
            this.setPassword(encoder.encode(user.getPassword()));
            this.setStatus(user.getStatus());
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> authorityList = new ArrayList<>();

                //赋予权限
                if(this.getStatus().equals("2")){
                    authorityList.add(new SimpleGrantedAuthority("ADMIN"));
                    System.out.println("super admin");
                }
                else if(this.getStatus().equals("1")){
                    authorityList.add(new SimpleGrantedAuthority("USER"));
                }

                return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return !this.getStatus().equals("0");
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return !this.getStatus().equals("0");
            }

}
