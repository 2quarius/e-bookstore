package com.sixplus.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private String commentId;
    private String userName;
    @NotEmpty
    private String context;
}
