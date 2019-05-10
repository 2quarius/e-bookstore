package com.sixplus.demo.controller;

import com.sixplus.demo.bean.Comment;
import com.sixplus.demo.bean.CommentDeleteRequest;
import com.sixplus.demo.bean.CommentRequest;
import com.sixplus.demo.bean.WebResponse;
import com.sixplus.demo.entity.BookComment;
import com.sixplus.demo.repository.BookCommentRepository;
import com.sixplus.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;

@CrossOrigin(origins = {"http://localhost:8081","null"},allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE})
//增、删、查，不能改评论
@RestController
@RequestMapping(value = "/book_comment")
public class BookCommentController {
    @Autowired
    private BookCommentRepository bookCommentRepository;
    @Autowired
    private UserRepository userRepository;
    private Comment findComment(BookComment bc,String commentId)
    {
        Vector<Comment> comments = bc.getComments();
        return recursiveFind(comments,commentId);
    }
    private Comment recursiveFind(Vector<Comment> comments,String commentId){
        if (comments.isEmpty()){
            return null;
        }
        for (Comment c:comments){
            if (c.commentId.equals(commentId)){
                return c;
            }
            else {
                Comment com = recursiveFind(c.children,commentId);
                if (com == null){
                    continue;
                }
                return com;
            }
        }
        return null;
    }
    @GetMapping(value = "/{book_id}")
    public WebResponse getComment(@PathVariable(value = "book_id") Integer bookId)
    {
        if (bookCommentRepository.existsById(bookId)){
            return WebResponse.success(bookCommentRepository.getById(bookId));
        }
        else {
            return WebResponse.success(null);
        }
    }
    @PostMapping(value = "/{book_id}")
    public WebResponse addComment(@PathVariable(value = "book_id") Integer bookId,
                                  @Valid @RequestBody CommentRequest content,
                                  BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()){
            return WebResponse.error();
        }
        else {
            BookComment bookComment;
            //如果是直接在book下评论
            if (content.getCommentId().isEmpty()){
                //如果该book以前有过评论
                if (bookCommentRepository.existsById(bookId)){
                    bookComment = bookCommentRepository.getById(bookId);
                    Vector<Comment> children = new Vector<>();
                    Integer userId = userRepository.findByUsername(content.getUserName()).getId();
                    Comment comment = new Comment(UUID.randomUUID().toString(),
                            new Date(System.currentTimeMillis()),
                            userId,
                            content.getUserName(), null,
                            new Integer(0),
                            content.getContext(),
                            children);
                    bookComment.getComments().add(comment);
                    bookCommentRepository.save(bookComment);
                }
                //如果该book以前没有评论
                else {
                    Vector<Comment> children = new Vector<>();
                    Integer userId = userRepository.findByUsername(content.getUserName()).getId();
                    Comment comment = new Comment(UUID.randomUUID().toString(),
                            new Date(System.currentTimeMillis()),
                            userId,
                            content.getUserName(), null,
                            new Integer(0),
                            content.getContext(),
                            children);
                    Vector<Comment> comments = new Vector<>();
                    comments.add(comment);
                    bookComment = new BookComment(bookId,comments);
                    bookCommentRepository.save(bookComment);
                }
            }
            //在某一个评论用户下评论
            else {
                if (bookCommentRepository.existsById(bookId)){
                    bookComment = bookCommentRepository.getById(bookId);
                    Comment comment = findComment(bookComment,content.getCommentId());
                    Vector<Comment> children = new Vector<>();
                    System.out.println(content.getUserName());
                    Integer userId = userRepository.findByUsername(content.getUserName()).getId();
                    Comment newComment = new Comment(UUID.randomUUID().toString(),
                            new Timestamp(System.currentTimeMillis()),
                            userId,content.getUserName(),comment.userName,new Integer(0),
                            content.getContext(),
                            children);
                    comment.children.add(newComment);
                    bookCommentRepository.save(bookComment);
                }
                //如果这个book根本就没有书评，那么此次操作是错误的
                else {
                    return WebResponse.error();
                }
            }
            return WebResponse.success(bookComment);
        }
    }
    @DeleteMapping(value = "/{book_id}")
    public WebResponse deleteComment(@PathVariable(value = "book_id") Integer bookId,
                                     @Valid @RequestBody CommentDeleteRequest content,
                                     BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()){
            return WebResponse.error();
        }
        else {
            BookComment bookComment;
            if (bookCommentRepository.existsById(bookId)){
                bookComment = bookCommentRepository.getById(bookId);
                Comment fatherComment = findComment(bookComment,content.getFatherCommentId());
                Comment deleteComment = recursiveFind(fatherComment.children,content.getDeleteCommentId());
                fatherComment.children.remove(deleteComment);
                bookCommentRepository.save(bookComment);
            }
            //如果该书没有评论记录，也就不能删除
            else {
                return WebResponse.error();
            }
            return WebResponse.success(bookComment);
        }
    }
}
