package com.example.loginsystem.mvp.presenters;

import androidx.annotation.NonNull;

import com.example.loginsystem.mvp.contracts.LoginActivityContract;
import com.example.loginsystem.mvp.contracts.RegisterActivityContract;
import com.example.loginsystem.mvp.shared.BaseContract;
import com.example.loginsystem.ui.RegisterActivity;

public class RegisterActivityPresenter implements RegisterActivityContract.Presenter {

    private  RegisterActivityContract.View view;

    public RegisterActivityPresenter(RegisterActivityContract.View view){
        setView(view);

    }

    @Override
    public void register(String name, String pass) {
        if (name.trim().isEmpty() || pass.trim().isEmpty()) {
            view.stateEmpty();
            return;
        }

        view.stateSucess(name,pass);
    }

    @Override
    public void setView(@NonNull RegisterActivityContract.View view) {
        this.view = view;
    }
}
