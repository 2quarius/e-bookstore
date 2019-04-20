package com.sixplus.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    private String dateOrBuyername;
    private Long SUBJECT_KEJI_SALES;
    private Long SUBJECT_SHENGHUO_SALES;
    private Long SUBJECT_WENXUE_SALES;
}
