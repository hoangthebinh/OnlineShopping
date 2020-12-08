package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.onlineshopping.Adapter.OrderAdapter;
import com.example.onlineshopping.Adapter.ProductAdapter;
import com.example.onlineshopping.Model.Account;
import com.example.onlineshopping.Model.Interfaces.OrderSelected;
import com.example.onlineshopping.Model.Order;
import com.example.onlineshopping.Resources.AccountManager;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(AccountManager.getInstance().getAccount().viewOrders(), this, new OrderSelected() {
            @Override
            public void didTapOrder(Order order) {
                Intent intent = new Intent(OrderActivity.this, OrderDetailActivity.class);
                intent.putExtra("order", order);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(orderAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        orderAdapter.setOrders(AccountManager.getInstance().getAccount().viewOrders());
        orderAdapter.notifyDataSetChanged();
    }
}