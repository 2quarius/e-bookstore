package com.sixplus.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteRequest {
    @NotEmpty
    private String fatherCommentId;
    @NotEmpty
    private String deleteCommentId;
}
