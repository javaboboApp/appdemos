package com.example.demoapi.di.components;

import android.content.Context;

import com.example.demoapi.data.repository.PostRepository;
import com.example.demoapi.di.modules.ApplicationModule;
import com.example.demoapi.di.modules.DataSourceModule;
import com.example.demoapi.di.modules.MainModule;
import com.example.demoapi.di.modules.NetworkModule;
import com.example.demoapi.di.modules.RepositoriesModule;
import com.example.demoapi.di.modules.SchedulerModule;
import com.example.demoapi.rx.SchedulerProvider;
import com.example.demoapi.ui.comments.CommentsFragment;
import com.example.demoapi.ui.posts.PostFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = {ApplicationModule.class, MainModule.class, NetworkModule.class, RepositoriesModule.class, SchedulerModule.class,
        DataSourceModule.class}) public interface MainComponent {

    void inject(PostFragment postFragment);

    void inject(CommentsFragment commentsFragment);

    Context context();

    SchedulerProvider schedulerProvider();

    PostRepository transactionRepository();
}
