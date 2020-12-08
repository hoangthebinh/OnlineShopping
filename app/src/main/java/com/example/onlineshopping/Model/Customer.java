package com.example.onlineshopping.Model;

import com.example.onlineshopping.Model.Enum.AccountType;
import com.example.onlineshopping.Model.Interfaces.AccountAuthorized;
import com.example.onlineshopping.Resources.AccountManager;

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

    public Customer(int id, AccountType type, String name, String username, String password, String address, String phoneNumber) {
        super(id, type, name, username, password);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
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

    public void makeOrder(int total, int customerId, String method, String products) {
        AccountManager.getInstance().getDatabase().queryData("INSERT INTO Orders VALUES(null, "+ total +", '"+ method +"', "+ customerId +", 0, 0, '" + products + "', 1, 0)");

    }

    public void cancelOrder(int id) {
        AccountManager.getInstance().getDatabase().queryData("DELETE FROM Orders WHERE id = " + id);
    }

}
