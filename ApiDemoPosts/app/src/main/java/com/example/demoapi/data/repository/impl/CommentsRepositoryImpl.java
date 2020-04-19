package com.example.demoapi.data.repository.impl;


import com.example.demoapi.data.repository.CommentRepository;
import com.example.demoapi.data.repository.datasource.CommentDataSource;
import com.example.demoapi.data.repository.datasource.mapper.CommentMapper;
import com.example.demoapi.mvp.model.Comment;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class CommentsRepositoryImpl implements CommentRepository {

    private CommentDataSource commentDataSource;
    private CommentMapper commentMapper;

    @Inject
    public CommentsRepositoryImpl(CommentDataSource commentDataSource,
                                  CommentMapper commentMapper) {
        this.commentDataSource = commentDataSource;
        this.commentMapper = commentMapper;
    }

    @Override
    public Observable<List<Comment>> getComments() {
        return commentDataSource.getComments().map(
                postEntity -> commentMapper.reverseMap(postEntity));
    }


}



