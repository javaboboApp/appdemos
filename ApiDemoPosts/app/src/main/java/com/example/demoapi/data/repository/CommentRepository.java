package com.example.demoapi.data.repository;


import com.example.demoapi.mvp.model.Comment;

import java.util.List;

import rx.Observable;

public interface CommentRepository {
    Observable<List<Comment>> getComments();

}
