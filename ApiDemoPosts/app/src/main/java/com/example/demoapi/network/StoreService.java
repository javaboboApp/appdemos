package com.example.demoapi.network;

import com.example.demoapi.data.entites.CommentEntity;
import com.example.demoapi.data.entites.PostEntity;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;


public interface StoreService {
    @GET("posts")
    Observable<List<PostEntity>> getPosts();
    @GET("posts/1/comments")
    Observable<List<CommentEntity>> getComments();
}
