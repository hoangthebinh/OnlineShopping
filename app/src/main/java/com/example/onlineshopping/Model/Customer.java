package com.example.onlineshopping.Model;

import com.example.onlineshopping.Model.Enum.AccountType;
import com.example.onlineshopping.Model.Interfaces.AccountAuthorized;

import java.util.ArrayList;

public class Customer extends Account {
    private ArrayList<Order> orders;
    private String address;
    private String phoneNumber;
    private ShoppingCart cart;

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Customer(AccountType type, String name, String username, String password, ArrayList<Order> orders, String address, String phoneNumber, ShoppingCart cart) {
        super(type, name, username, password);
        this.orders = orders;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void register() {};
    public void makeOrder() {};
    public void addToCart() {};
    public void deleteFromCart() {};
    public void makePayment() {};

}
