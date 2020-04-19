package com.example.demoapi.data.repository.datasource.remote;


import com.example.demoapi.data.entites.PostEntity;
import com.example.demoapi.data.repository.datasource.PostDataSource;
import com.example.demoapi.network.StoreService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RemotePostDataSource implements PostDataSource {

    private final StoreService storeService;

    @Inject
    public RemotePostDataSource(StoreService storeService) {
        this.storeService = storeService;
    }


    @Override
    public Observable<List<PostEntity>> getPosts() {
        return storeService.getPosts();
    }
}
