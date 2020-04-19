package com.example.demoapi.data.repository.datasource.remote;


import com.example.demoapi.data.entites.CommentEntity;
import com.example.demoapi.data.repository.datasource.CommentDataSource;
import com.example.demoapi.network.StoreService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RemoteCommentDataSource implements CommentDataSource {

    private final StoreService storeService;

    @Inject
    public RemoteCommentDataSource(StoreService storeService) {
        this.storeService = storeService;
    }


    @Override
    public Observable<List<CommentEntity>> getComments() {
        return storeService.getComments();
    }
}
