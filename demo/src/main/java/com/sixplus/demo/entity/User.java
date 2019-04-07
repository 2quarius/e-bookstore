package com.sixplus.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class User {
    @Id//用于声明一个实体类的属性映射位数据库的主键列
    @GeneratedValue
    //用于标注主键的生成策略，通过strategy属性指定，JPA自动选择一个最合适底层数据库的主键生成策略，mysql对应auto increment
    private Integer id;

    private String username;

    private String password;

    private String status;
}
