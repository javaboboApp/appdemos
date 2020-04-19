package com.example.demoapi;

import android.app.Application;
import android.content.Context;

import com.example.demoapi.di.components.DaggerMainComponent;
import com.example.demoapi.di.components.MainComponent;
import com.example.demoapi.di.modules.ApplicationModule;
import com.example.demoapi.di.modules.MainModule;
import com.example.demoapi.di.modules.NetworkModule;
import com.example.demoapi.utils.Constants;


public class App extends Application {
    private static Context context;



    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();


    }

    private void initializeInjector() {
        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this))
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

}
