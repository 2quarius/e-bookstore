package com.sixplus.demo.config;

import com.sixplus.demo.config.entryPoint.CustomAuthenticationEntryPoint;
import com.sixplus.demo.config.handlers.CustomAccessDeniedHandler;
import com.sixplus.demo.config.handlers.CustomAuthenticationFailureHandler;
import com.sixplus.demo.config.handlers.CustomAuthenticationSuccessHandler;
import com.sixplus.demo.config.handlers.CustomLogoutSuccessHandler;
import com.sixplus.demo.config.userDetails.UserDetailsServiceImpl;
import com.sixplus.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .failureHandler(new CustomAuthenticationFailureHandler())
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/user/{id}","/deals/user/{buyername}","/deals/user/rate","/deals/user/statistic/{buyername}","/cart/**","/book_comment/**").hasAuthority("USER")
                .antMatchers("/add/**","/delete/**","/set/**","/get/**","/deals/admin/statistic","/deals/admin/userSpecify","/deals").hasAuthority("ADMIN")
                .antMatchers("/","/users","/storages","/storages/{id}").permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .clearAuthentication(true).permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .csrf().disable();
    }

}