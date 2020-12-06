package com.example.onlineshopping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.onlineshopping.Adapter.ProductAdapter;
import com.example.onlineshopping.Model.Admin;
import com.example.onlineshopping.Model.Product;
import com.example.onlineshopping.Resources.AccountManager;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    Button add, edit, delete, cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        add = (Button) findViewById(R.id.add);
        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);
        cart = (Button) findViewById(R.id.cart);

        if (AccountManager.getInstance().getAccount().getType().equals("customer") ||  AccountManager.getInstance().getAccount().getType().equals("shipper"))
        {
            add.setVisibility(View.INVISIBLE);
            edit.setVisibility(View.INVISIBLE);
            delete.setVisibility(View.INVISIBLE);
        }
        else {
            cart.setVisibility(View.INVISIBLE);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ProductAdapter(AccountManager.getInstance().getAccount().viewProducts(),this);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductDialog();
            }
        });

    }

    private void showAddProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_add_product, findViewById(android.R.id.content), false);
        EditText name = (EditText) viewInflated.findViewById(R.id.name);
        EditText kind = (EditText) viewInflated.findViewById(R.id.kind);
        Spinner group = (Spinner) viewInflated.findViewById(R.id.spin);
        EditText price = (EditText) viewInflated.findViewById(R.id.price);

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((Admin) AccountManager.getInstance().getAccount()).addProduct(name.getText().toString(), kind.getText().toString(), group.getSelectedItem().toString(), price.getText().toString());
                adapter.setProducts(AccountManager.getInstance().getAccount().viewProducts());
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}