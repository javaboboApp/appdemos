package com.example.loginsystem.mvp.contracts;

import com.example.loginsystem.mvp.shared.BaseContract;

public interface RegisterActivityContract {
    interface View extends BaseContract.View {


        void stateError();

        void stateEmpty();

        void stateSucess(String user, String pass);


        ;
    }

    interface Presenter extends BaseContract.Presenter<RegisterActivityContract.View> {
        void register(String name, String pass);
    }
}
