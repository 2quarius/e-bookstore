package com.sixplus.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sixplus.demo.constant.OrderStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Deal {
    @Id
    @GeneratedValue
    private Long orderid;

    private Date date;

    private String buyername;

    private Integer goodid;

    private Double rate;

    private OrderStatusEnum statusEnum;

    private Integer number;

}
