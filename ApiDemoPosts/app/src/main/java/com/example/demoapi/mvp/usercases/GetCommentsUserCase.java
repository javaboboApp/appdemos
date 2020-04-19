package com.example.demoapi.mvp.usercases;

import com.example.demoapi.data.repository.CommentRepository;
import com.example.demoapi.mvp.model.Comment;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


public class GetCommentsUserCase {

    private CommentRepository commentRepository;

    @Inject
    public GetCommentsUserCase(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Inject
    public Observable<List<Comment>> getComments() {
        return commentRepository.getComments();
    }
}
