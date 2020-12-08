package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.onlineshopping.Model.Account;
import com.example.onlineshopping.Model.Admin;
import com.example.onlineshopping.Resources.AccountManager;

public class MainMenuActivity extends AppCompatActivity {

    LinearLayout productBtn, orderBtn, profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
        productBtn = (LinearLayout) findViewById(R.id.product);
        orderBtn = (LinearLayout) findViewById(R.id.orders);
        profileButton = (LinearLayout) findViewById(R.id.profile);

        productBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, ProductActivity.class));
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, OrderActivity.class));
            }
        });
    }
}