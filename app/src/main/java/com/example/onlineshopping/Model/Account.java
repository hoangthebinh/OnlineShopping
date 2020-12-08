package com.example.onlineshopping.Model;

import android.database.Cursor;

import com.example.onlineshopping.Model.Enum.AccountType;
import com.example.onlineshopping.Model.Enum.PaymentMethod;
import com.example.onlineshopping.Model.Interfaces.AccountAuthorized;
import com.example.onlineshopping.Resources.AccountManager;

import java.util.ArrayList;

public abstract class Account implements AccountAuthorized {
    private int id;
    private AccountType type;

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    private String name;
    private String username;
    private String password;

    public Account(AccountType type, String name, String username, String password) {
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Account(int id, AccountType type, String name, String username, String password) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Account() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ArrayList<Product> viewProducts() {
        Cursor data = AccountManager.getInstance().getDatabase().getData("SELECT * FROM Product");
        ArrayList<Product> products = new ArrayList<>();
        while (data.moveToNext()) {
            Product product = new Product();
            product.setId(data.getInt(0));
            product.setName(data.getString(1));
            product.setKind(data.getString(2));
            product.setGroup(data.getString(3));
            product.setPrice(data.getInt(4));
            products.add(product);
        }
        return products;
    }

    @Override
    public ArrayList<Order> viewOrders() {
        Cursor data = AccountManager.getInstance().getDatabase().getData("SELECT * FROM Orders");
        ArrayList<Order> orders = new ArrayList<>();
        while (data.moveToNext()) {
            Order order = new Order();
            order.setId(data.getInt(0));
            order.setPrice(data.getInt(1));
            order.setMethod(data.getString(2).equals("CREDIT") ? PaymentMethod.CREDIT_CARD : PaymentMethod.CASH);
            order.setCustomerId(data.getInt(3));
            order.setShipperId(data.getInt(4));
            order.setAdminId(data.getInt(5));
            order.setProducts(data.getString(6));
            order.setPending(data.getInt(7) == 0 ? false : true);
            order.setPaid(data.getInt(8) == 0 ? false : true);

            orders.add(order);
        }
        return orders;
    }

    @Override
    public Product getProductById(String id) {
        Cursor data = AccountManager.getInstance().getDatabase().getData("SELECT * FROM Product WHERE id = " + id);
        Product product = new Product();
        while (data.moveToNext()) {
            product.setId(data.getInt(0));
            product.setName(data.getString(1));
            product.setKind(data.getString(2));
            product.setGroup(data.getString(3));
            product.setPrice(data.getInt(4));
        }
        return product;
    }

    @Override
    public Product getProductById(int id) {
        Cursor data = AccountManager.getInstance().getDatabase().getData("SELECT * FROM Product WHERE id = " + id);
        Product product = new Product();
        if (data.moveToFirst()) {
            product.setId(data.getInt(0));
            product.setName(data.getString(1));
            product.setKind(data.getString(2));
            product.setGroup(data.getString(3));
            product.setPrice(data.getInt(4));
        }
        return product;
    }

    @Override
    public void changePassword() {

    }
}
