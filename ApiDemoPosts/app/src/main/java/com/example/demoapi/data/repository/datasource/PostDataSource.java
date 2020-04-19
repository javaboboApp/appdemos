package com.example.demoapi.data.repository.datasource;


import com.example.demoapi.data.entites.PostEntity;

import java.util.List;

import rx.Observable;

public interface PostDataSource {
    Observable<List<PostEntity>> getPosts();




}
