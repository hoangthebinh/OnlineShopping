package com.example.onlineshopping.Model.Interfaces;

import com.example.onlineshopping.Model.Order;
import com.example.onlineshopping.Model.Product;

import java.util.ArrayList;

public interface AccountAuthorized {

    ArrayList<Product> viewProducts();
    ArrayList<Order> viewOrders();
    Product getProductById(String id);

    Product getProductById(int id);
    void changePassword();

}
