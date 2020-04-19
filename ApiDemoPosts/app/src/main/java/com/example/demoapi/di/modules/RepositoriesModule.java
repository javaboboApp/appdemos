package com.example.demoapi.di.modules;



import com.example.demoapi.data.repository.CommentRepository;
import com.example.demoapi.data.repository.PostRepository;
import com.example.demoapi.data.repository.datasource.CommentDataSource;
import com.example.demoapi.data.repository.datasource.PostDataSource;
import com.example.demoapi.data.repository.datasource.mapper.CommentMapper;
import com.example.demoapi.data.repository.datasource.mapper.PostMapper;
import com.example.demoapi.data.repository.impl.CommentsRepositoryImpl;
import com.example.demoapi.data.repository.impl.PostRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Singleton
    @Provides
    PostRepository providePostRepositoryImpl(PostDataSource postDataSource,
                                             PostMapper postMapper) {
        return new PostRepositoryImpl(postDataSource,postMapper);
    }


    @Singleton
    @Provides
    PostMapper providePostMapper() {
        return new PostMapper();
    }


    @Singleton
    @Provides
    CommentRepository provideCommentsRepositoryImpl(CommentDataSource commentDataSource,
                                                   CommentMapper commentMapper) {
        return new CommentsRepositoryImpl(commentDataSource,commentMapper);
    }


    @Singleton
    @Provides
    CommentMapper provideCommentMapper() {
        return new CommentMapper();
    }


}
