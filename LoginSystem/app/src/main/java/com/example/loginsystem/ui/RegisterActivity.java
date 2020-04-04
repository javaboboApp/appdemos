package com.example.loginsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginsystem.R;
import com.example.loginsystem.Utils;
import com.example.loginsystem.mvp.contracts.RegisterActivityContract;
import com.example.loginsystem.mvp.presenters.RegisterActivityPresenter;
import com.example.loginsystem.mvp.shared.BaseContract;
import com.example.loginsystem.network.FakeServer;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityContract.View {
    private EditText username;
    private EditText password;
    private Button register;

    private RegisterActivityContract.Presenter presenter;

    public static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpViews();
        setUpListerners();
        setUpPresenter();
    }

    private void setUpListerners() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                presenter.register(user, pass);
            }
        });
    }



    private void setUpViews() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.btn_register);
    }

    private void setUpPresenter() {
        this.presenter = new RegisterActivityPresenter(this);
    }


    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {
        Utils.showToast(RegisterActivity.this, R.string.msg_toast_error_user_pass, Toast.LENGTH_LONG);

    }

    @Override
    public void stateSucess(String user, String pass) {
        FakeServer fakeServer = new FakeServer();
        fakeServer.registerUser(user, pass);
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra(TAG, fakeServer);
        startActivity(intent);
    }
}
