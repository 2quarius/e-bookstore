package com.sixplus.demo.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Vector;

@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    public String commentId;
    public Date date;
    public Integer userId;
    public String userName;
    public String fatherName;
    public Integer likeNum;
    public String context;
    public Vector<Comment> children;
}
