package com.example.demoapi.di.modules;

import com.example.demoapi.data.repository.datasource.CommentDataSource;
import com.example.demoapi.data.repository.datasource.PostDataSource;
import com.example.demoapi.data.repository.datasource.remote.RemoteCommentDataSource;
import com.example.demoapi.data.repository.datasource.remote.RemotePostDataSource;
import com.example.demoapi.network.StoreService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = NetworkModule.class
)
public class DataSourceModule {

    @Provides
    @Singleton
    PostDataSource provideRemoteDataSource(StoreService storeService) {
        return new RemotePostDataSource(storeService);
    }

    @Provides
    @Singleton
    CommentDataSource provideRemoteCommentDataSource(StoreService storeService) {
        return new RemoteCommentDataSource(storeService);
    }



}
