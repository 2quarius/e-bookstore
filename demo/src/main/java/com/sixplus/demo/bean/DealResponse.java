package com.sixplus.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealResponse {
    private String id;
    private String date;
    private Map<String,Integer> goodnameToNumber;
    private Double rate;
}
