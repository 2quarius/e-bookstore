package com.sixplus.demo.repository;

import com.sixplus.demo.entity.BookComment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookCommentRepository extends MongoRepository<BookComment,Integer> {
    BookComment getById(Integer id);
}
