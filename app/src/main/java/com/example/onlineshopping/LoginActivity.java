package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlineshopping.Resources.AccountManager;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginButton;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.username);
        loginButton = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(username.getText().toString(), password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void login(String username, String password) {
        if (AccountManager.getInstance().login(username, password)) {
            startActivity(new Intent(this, MainMenuActivity.class));
        }
    }


}