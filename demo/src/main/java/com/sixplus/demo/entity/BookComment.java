package com.sixplus.demo.entity;

import com.sixplus.demo.bean.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import java.util.Vector;

@Data
@AllArgsConstructor
public class BookComment {
    @Id
    private Integer id;

    private Vector<Comment> comments;
    @Override
    public String toString(){
        return String.format("BookComment[id=%s, comments='%s']",this.id,this.comments);
    }
}
