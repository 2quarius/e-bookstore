package com.sixplus.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private Integer id;
    private String url;
    private String name;
    private Double price;
    private Integer number;
}
