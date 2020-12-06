package com.example.onlineshopping.Model;

import java.util.ArrayList;

public class ShoppingCart {

    private int id;
    private ArrayList<Product> products;

    public ShoppingCart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    void calculateTotalPrice() {};
    void pushOrder() {};

}
