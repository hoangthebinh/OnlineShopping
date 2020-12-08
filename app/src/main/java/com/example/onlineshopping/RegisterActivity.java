package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.DrawableMarginSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.onlineshopping.Model.Customer;
import com.example.onlineshopping.Resources.AccountManager;

public class RegisterActivity extends AppCompatActivity {

    EditText name, username, phone, address, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer();
                AccountManager.getInstance().registerNewCustomer(username.getText().toString(), password.getText().toString(), name.getText().toString(), address.getText().toString(), phone.getText().toString());
                startActivity(new Intent(RegisterActivity.this, MainMenuActivity.class));
            }
        });

    }
}