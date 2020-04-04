package com.example.loginsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.loginsystem.R;
import com.example.loginsystem.Utils;

public class WelcomeActivity extends AppCompatActivity {
private TextView welcome;
private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        username = getIntent().getStringExtra(LoginActivity.TAG);
        setUpViews();
    }

    private void setUpViews() {
        this.welcome = findViewById(R.id.welcome_msg);
        this.welcome.setText(Utils.getResourceString(WelcomeActivity.this, R.string.activity_welcome_msg_) + " " + username);
    }
}
