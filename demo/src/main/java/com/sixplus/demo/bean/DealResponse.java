package com.sixplus.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealResponse {
    private Long id;
    private String date;
    private String goodname;
    private Integer number;
    private Double rate;
}
