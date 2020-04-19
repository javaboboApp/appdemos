package com.example.demoapi.data.repository.datasource;


import com.example.demoapi.data.entites.CommentEntity;

import java.util.List;

import rx.Observable;

public interface CommentDataSource {
    Observable<List<CommentEntity>> getComments();




}
