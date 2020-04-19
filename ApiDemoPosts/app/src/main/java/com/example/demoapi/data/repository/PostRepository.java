package com.example.demoapi.data.repository;


import com.example.demoapi.mvp.model.Post;

import java.util.List;

import rx.Observable;

public interface PostRepository {
    Observable<List<Post>> getPosts();

}
