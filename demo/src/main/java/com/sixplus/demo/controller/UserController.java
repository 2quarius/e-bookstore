package com.sixplus.demo.controller;

import com.sixplus.demo.bean.WebResponse;
import com.sixplus.demo.entity.User;
import com.sixplus.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"*","null"})
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private Integer generateId()
    {
        System.out.println(userRepository);
        List<User> users = userRepository.findAll();
        System.out.println(users);
        if(users.isEmpty()){return 1;}
        else {
            if(users.get(0).getId()>users.get(users.size()-1).getId()){
                Integer tmp = users.get(0).getId();
                return ++tmp;
            }
            else {
                Integer tmp = users.get(users.size()-1).getId();
                return ++tmp;
            }
        }
    }

    @GetMapping(value = "get/users")//管理员获取所有用户的信息表
    public List<User> getUsers(){return userRepository.findAll();}


    @PostMapping(value = "set/users/{id}")//管理员禁用/解禁用户
    public User disableUser(@PathVariable(value = "id") Integer id,
                            @RequestParam(value = "status",required = false) String status)
    {
        Optional<User> users= userRepository.findById(id);
        User user = users.orElse(new User());
        user.setStatus(status);
        return userRepository.save(user);
    }

    @PostMapping(value = "/users")//用户申请账号
    public WebResponse addUsers(@RequestParam(value = "password",required = false) String password,
                         @RequestParam(value = "username",required = false) String username,
                         @RequestParam(value = "status",required = false) String status)
    {
        if(userRepository.findByUsername(username)!=null){
            WebResponse response = WebResponse.success("用户名已存在");
            return response;
        }
        else {
            User user = new User();
            Integer id = generateId();
            getUser(password, username, status, user, id);
            WebResponse response = WebResponse.success("注册成功");
            return response;
        }
    }

    private User getUser(@RequestParam("password") String password, @RequestParam("username") String username,@RequestParam("status") String status, User user, Integer id) {
        user.setId(id);
        user.setPassword(password);
        user.setUsername(username);
        user.setStatus(status);
        return userRepository.save(user);
    }

    @PutMapping(value = "/users/{id}")//用户登录后才可修改用户名和密码
    public WebResponse updateUsers(@PathVariable(value = "id") Integer id,
                            @RequestParam(value = "password",required = false) String password,
                            @RequestParam(value = "username",required = false) String username)
    {
        Optional<User> users= userRepository.findById(id);
        User user = users.orElse(new User());
        if(userRepository.findByUsername(username)==null)
        {
            user.setPassword(password);
            user.setUsername(username);
        }
        userRepository.save(user);
        WebResponse response = WebResponse.success("修改成功");
        return response;
    }
}
