package com.example.onlineshopping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.onlineshopping.Adapter.ProductAdapter;
import com.example.onlineshopping.Model.Account;
import com.example.onlineshopping.Model.Admin;
import com.example.onlineshopping.Model.Enum.AccountType;
import com.example.onlineshopping.Model.Interfaces.ProductSelected;
import com.example.onlineshopping.Model.Product;
import com.example.onlineshopping.Resources.AccountManager;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductSelected {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    Button add, edit, delete, cart;
    ArrayList<Product> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        add = (Button) findViewById(R.id.add);
        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);
        cart = (Button) findViewById(R.id.cart);

        if (AccountManager.getInstance().getAccount().getType() == AccountType.CUSTOMER || AccountManager.getInstance().getAccount().getType() == AccountType.SHIPPER)
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
        productAdapter = new ProductAdapter(AccountManager.getInstance().getAccount().viewProducts(),this, this);
        recyclerView.setAdapter(productAdapter);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.setBackgroundResource(R.drawable.cart);
                Intent intent = new Intent(ProductActivity.this, ShoppingCartActivity.class);
                intent.putExtra("productList", cartItems);
                startActivity(intent);
            }
        });

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
                productAdapter.setProducts(AccountManager.getInstance().getAccount().viewProducts());
                productAdapter.notifyDataSetChanged();
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

    private void showModifyProductDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_modify_product, findViewById(android.R.id.content), false);
        EditText name = (EditText) viewInflated.findViewById(R.id.name);
        EditText kind = (EditText) viewInflated.findViewById(R.id.kind);
        EditText price = (EditText) viewInflated.findViewById(R.id.price);

        Spinner group = (Spinner) viewInflated.findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        group.setAdapter(adapter);

        name.setText(product.getName());
        kind.setText(product.getKind());
        if (product.getGroup() != null)
            group.setSelection(adapter.getPosition(product.getGroup()));
        price.setText(""  + product.getPrice());

        builder.setView(viewInflated);

        builder.setPositiveButton("MODIFY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Product modifiedProduct = new Product(product.getId(), name.getText().toString(), kind.getText().toString(), group.getSelectedItem().toString(), Integer.parseInt(price.getText().toString()));
                ((Admin) AccountManager.getInstance().getAccount()).modifyProduct(modifiedProduct);
                productAdapter.setProducts(AccountManager.getInstance().getAccount().viewProducts());
                productAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("REMOVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new AlertDialog.Builder(ProductActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete Product")
                        .setMessage("Are you sure you want to delete this product?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((Admin) AccountManager.getInstance().getAccount()).deleteProduct(product);
                                productAdapter.setProducts(AccountManager.getInstance().getAccount().viewProducts());
                                productAdapter.notifyDataSetChanged();
                                dialog.cancel();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        builder.show();
    }

    @Override
    public void didTapProduct(Product product) {
        if (AccountManager.getInstance().getAccount().getType() == AccountType.ADMIN) {
            showModifyProductDialog(product);
        }
        else {
            // TODO: User add product to cart
            cart.setBackgroundResource(R.drawable.cart_notify);
            Toast.makeText(this, "Added "+ product.getName() +" to your cart", Toast.LENGTH_SHORT).show();
            cartItems.add(product);
        }

    }
}