package com.sixplus.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sixplus.demo.constant.OrderStatusEnum;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Deal {
    @Id
    private String orderid;

    private Date date;

    private String buyername;

    @ElementCollection
    private Map<Integer,Integer> goodidToNumber;

    private Double rate;

    private OrderStatusEnum statusEnum;

}
