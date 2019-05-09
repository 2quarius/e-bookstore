package com.sixplus.demo.config.userDetails;

import com.sixplus.demo.config.userDetails.MyUserDetails;
import com.sixplus.demo.entity.User;
import com.sixplus.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserDetails userDetails;
        User user = userRepository.findByUsername(username);
        return new MyUserDetails(user);
    }
}
