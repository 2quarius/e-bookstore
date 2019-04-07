package com.sixplus.demo.service;

import com.sixplus.demo.entity.User;

public interface UserService {
    void insert(User user);
    void delete(User user);
    User getByUsername(String username);
}
