package com.example.demoapi.mvp.usercases;

import com.example.demoapi.data.repository.PostRepository;
import com.example.demoapi.mvp.model.Post;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


public class GetPostsUserCase {

    private PostRepository postRepository;

    @Inject
    public GetPostsUserCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Inject
    public Observable<List<Post>> getPost() {
        return postRepository.getPosts();
    }
}
