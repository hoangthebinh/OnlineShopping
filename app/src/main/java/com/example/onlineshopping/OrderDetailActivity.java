package com.example.onlineshopping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.Adapter.ProductAdapter;
import com.example.onlineshopping.Model.Account;
import com.example.onlineshopping.Model.Admin;
import com.example.onlineshopping.Model.Customer;
import com.example.onlineshopping.Model.Order;
import com.example.onlineshopping.Model.Product;
import com.example.onlineshopping.Resources.AccountManager;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderDetailActivity extends AppCompatActivity {

    TextView id, from, method, admin, shipper, condition, payment, product, price;
    RecyclerView recyclerView;
    Button cancel;
    Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        order = (Order) getIntent().getSerializableExtra("order");

        id              = (TextView) findViewById(R.id.id);
        from            = (TextView) findViewById(R.id.from);
        method          = (TextView) findViewById(R.id.method);
        admin           = (TextView) findViewById(R.id.admin);
        shipper         = (TextView) findViewById(R.id.shipper);
        condition       = (TextView) findViewById(R.id.condition);
        payment         = (TextView) findViewById(R.id.payment);
        product         = (TextView) findViewById(R.id.product);
        price           = (TextView) findViewById(R.id.price);
        recyclerView    = (RecyclerView) findViewById(R.id.recyclerView);
        cancel          = (Button) findViewById(R.id.cancel);

        id           .setText(id.getText() + "" + order.getId());
        from         .setText(from.getText() + "" + order.getCustomerId());
        method       .setText(method.getText() + "" + order.getMethod());
        admin        .setText(admin.getText() + "" + order.getAdminId());
        shipper      .setText(shipper.getText() + "" + order.getShipperId());
        condition    .setText(order.isPending() ? condition.getText() + "" + "Pending" : condition.getText() + "" + "Delivery");
        payment      .setText(order.isPaid() ? payment.getText() + "" + "Paid" : payment.getText() + "" + "Not paid");
        price        .setText(price.getText() + "" + order.getPrice() + " $");

        ArrayList<Product> productList = new ArrayList<>();

        String[] parts = order.getProducts().split(":");
        for (String part: parts) {
            productList.add(AccountManager.getInstance().getAccount().getProductById(part));
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ProductAdapter productAdapter = new ProductAdapter(productList,this);
        recyclerView.setAdapter(productAdapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(OrderDetailActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Cancel Order")
                        .setMessage("Are you sure you want to cancel your order?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                ((Customer) AccountManager.getInstance().getAccount()).cancelOrder(order.getId());
                                Toast.makeText(OrderDetailActivity.this, "Cancel order successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }





}