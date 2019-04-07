package com.sixplus.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
//报错原因：是因为在实体类中 发现有字段为null，在转化成json的时候，fasterxml.jackson将对象转换为json报错
public class Storage {
    @Id
    @GeneratedValue
    private Integer id;

    private String url;

    private String name;

    private String ISBN;

    private Double price;

    private Integer stores;

    private String descript;

    private String subject;
}
