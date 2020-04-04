package com.example.loginsystem.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginsystem.R;
import com.example.loginsystem.Utils;
import com.example.loginsystem.mvp.contracts.LoginActivityContract;
import com.example.loginsystem.mvp.presenters.LoginActivityPresenter;
import com.example.loginsystem.mvp.shared.BaseContract;
import com.example.loginsystem.network.FakeServer;

public class LoginActivity extends AppCompatActivity implements LoginActivityContract.View {

    private LoginActivityContract.Presenter presenter;

    private EditText username;
    private EditText password;
    private Button login;
    public static final String TAG = "LoginActivity";
    private FakeServer fakeServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         fakeServer = (FakeServer) getIntent().getParcelableExtra(RegisterActivity.TAG);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpViews();
        setUpListeners();
        setUpPresenter(fakeServer);

    }

    private void setUpViews() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
    }

    private void setUpListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                presenter.login(user, pass);
            }
        });
    }

    private void setUpPresenter(FakeServer fakeServer) {
        presenter = new LoginActivityPresenter(this, fakeServer);
    }


    @Override
    public void stateError() {
        Utils.showToast(LoginActivity.this, R.string.msg_error_user_pass_login, Toast.LENGTH_LONG);
    }

    @Override
    public void stateEmpty() {
        Utils.showToast(LoginActivity.this, R.string.msg_toast_error_user_pass, Toast.LENGTH_LONG);
    }

    @Override
    public void stateSucess() {
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        intent.putExtra(TAG, fakeServer.getUsername());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
