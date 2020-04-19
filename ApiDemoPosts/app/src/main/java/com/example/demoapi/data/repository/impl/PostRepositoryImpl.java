package com.example.demoapi.data.repository.impl;


import com.example.demoapi.data.repository.PostRepository;
import com.example.demoapi.data.repository.datasource.PostDataSource;
import com.example.demoapi.data.repository.datasource.mapper.PostMapper;
import com.example.demoapi.mvp.model.Post;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class PostRepositoryImpl implements PostRepository {

    private PostDataSource postDataSource;
    private PostMapper postMapper;

    @Inject
    public PostRepositoryImpl(PostDataSource postDataSource,
                              PostMapper postMapper) {
        this.postDataSource = postDataSource;
        this.postMapper = postMapper;
    }

    @Override
    public Observable<List<Post>> getPosts() {
        return postDataSource.getPosts().map(
                postEntity -> postMapper.reverseMap(postEntity));
    }


}



