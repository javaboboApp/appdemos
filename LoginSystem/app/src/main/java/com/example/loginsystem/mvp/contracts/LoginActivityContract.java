package com.example.loginsystem.mvp.contracts;

import com.example.loginsystem.mvp.shared.BaseContract;

public interface  LoginActivityContract {
    interface View extends BaseContract.View {


        void stateError();

        void stateEmpty();

        void stateSucess();



    }

    interface Presenter extends BaseContract.Presenter<View> {
        void login(String name, String pass);
    }
}
