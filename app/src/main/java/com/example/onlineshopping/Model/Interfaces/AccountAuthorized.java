package com.example.onlineshopping.Model.Interfaces;

import com.example.onlineshopping.Model.Product;

import java.util.ArrayList;

public interface AccountAuthorized {

    ArrayList<Product> viewProducts();
    void changePassword();

}
