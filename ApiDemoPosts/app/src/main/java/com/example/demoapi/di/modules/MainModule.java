package com.example.demoapi.di.modules;

import android.content.Context;

import com.example.demoapi.App;
import com.example.demoapi.mvp.contracts.CommentsContract;
import com.example.demoapi.mvp.contracts.PostsContract;
import com.example.demoapi.mvp.presenters.CommentsFragmentPresenter;
import com.example.demoapi.mvp.presenters.PostsFragmentPresenter;
import com.example.demoapi.mvp.usercases.GetCommentsUserCase;
import com.example.demoapi.mvp.usercases.GetPostsUserCase;
import com.example.demoapi.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class MainModule {

    private final App app;

    public MainModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app;
    }


    @Provides
    PostsContract.Presenter provideMainActivityPresenter(SchedulerProvider schedulerProvider,
                                                         GetPostsUserCase getPostsUserCase) {
        return new PostsFragmentPresenter(schedulerProvider, getPostsUserCase);
    }


    @Provides
    CommentsContract.Presenter provideCommentsFragmentPresenter(SchedulerProvider schedulerProvider, GetCommentsUserCase getCommentsUserCase) {
        return new CommentsFragmentPresenter(schedulerProvider, getCommentsUserCase);
    }



}
