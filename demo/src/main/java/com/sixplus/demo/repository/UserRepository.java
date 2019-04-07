package com.sixplus.demo.repository;

import com.sixplus.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface UserRepository extends JpaRepository<User, Integer> {
    //find by id
    List<User> findAllByOrderById();
    List<User> findAll();
    User findByUsername(String username);
    User deleteByUsername(String username);

}
