package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.onlineshopping.Model.Account;
import com.example.onlineshopping.Model.Admin;
import com.example.onlineshopping.Resources.AccountManager;

public class MainMenuActivity extends AppCompatActivity {

    LinearLayout productBtn, orderBtn, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productBtn = (LinearLayout) findViewById(R.id.product);
        orderBtn = (LinearLayout) findViewById(R.id.orders);
        profileButton = (LinearLayout) findViewById(R.id.profile);

        productBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, ProductActivity.class));
            }
        });

    }
}