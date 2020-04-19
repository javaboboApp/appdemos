package com.example.demoapi.di.modules;


import com.example.demoapi.rx.SchedulerProvider;
import com.example.demoapi.rx.SchedulerProviderImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SchedulerModule {

    @Binds
    public abstract SchedulerProvider bindSchedulerProvider(SchedulerProviderImpl schedulerProvider);
}
