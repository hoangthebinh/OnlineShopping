package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.Adapter.ProductAdapter;
import com.example.onlineshopping.Model.Account;
import com.example.onlineshopping.Model.Customer;
import com.example.onlineshopping.Model.Interfaces.ProductSelected;
import com.example.onlineshopping.Model.Product;
import com.example.onlineshopping.Resources.AccountManager;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements ProductSelected {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    TextView back, total;
    Button order;
    ArrayList<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        productList = (ArrayList<Product>) getIntent().getSerializableExtra("productList");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        back = (TextView) findViewById(R.id.back);
        total = (TextView) findViewById(R.id.total);
        order = (Button) findViewById(R.id.order);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        productAdapter = new ProductAdapter(productList,this, this);
        recyclerView.setAdapter(productAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final int totalPrice = countTotalPrice();
        total.setText("TOTAL: " + totalPrice + " $");

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderText = "";
                for (Product product: productList) {
                    orderText += "" + product.getId() + ":";
                }
                Log.d("productlist", "onClick: " + orderText);
                ((Customer) AccountManager.getInstance().getAccount()).makeOrder(totalPrice, ((Customer) AccountManager.getInstance().getAccount()).getId(), "CREDIT", orderText);
                Toast.makeText(ShoppingCartActivity.this, "Create new order successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    private int countTotalPrice() {
        int totalPrice = 0;
        for (Product product: productList) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    @Override
    public void didTapProduct(Product product) {

    }
}