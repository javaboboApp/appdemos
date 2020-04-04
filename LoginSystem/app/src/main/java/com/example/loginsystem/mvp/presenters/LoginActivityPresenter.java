package com.example.loginsystem.mvp.presenters;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.loginsystem.R;
import com.example.loginsystem.Utils;
import com.example.loginsystem.mvp.contracts.LoginActivityContract;
import com.example.loginsystem.mvp.shared.BaseContract;

import com.example.loginsystem.network.FakeServer;
import com.example.loginsystem.ui.LoginActivity;
import com.example.loginsystem.ui.RegisterActivity;

public class LoginActivityPresenter implements LoginActivityContract.Presenter {
    private LoginActivityContract.View view;
    private FakeServer fakeServer;

    public LoginActivityPresenter(LoginActivityContract.View view, FakeServer fakeServer) {
        setView(view);
        this.fakeServer = fakeServer;

    }

    @Override
    public void setView(@NonNull LoginActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String name, String pass) {
        if (name.trim().isEmpty() || pass.trim().isEmpty()) {
            view.stateEmpty();
            return;
        }
        if (!fakeServer.isExistingUser(name, pass)) {
            view.stateError();
            return;
        }

        view.stateSucess();

    }
}
