package com.sixplus.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Cart extends Object implements Serializable {
    @Id
    private Integer goodid;
    private Integer number;
    @Override
    public String toString(){
        return "Cart [goodid="+goodid+",number="+number+"]";
    }
}
