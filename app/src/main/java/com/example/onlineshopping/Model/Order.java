package com.example.onlineshopping.Model;

import com.example.onlineshopping.Model.Enum.PaymentMethod;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int price;
    private PaymentMethod method;
    private int customerId;
    private int shipperId;
    private int adminId;
    private String products;
    private boolean isPending;
    private boolean isPaid;

    public Order() {
    }

    public Order(int id, int price, PaymentMethod method, int customerId, int shipperId, int adminId, String products, boolean isPending, boolean isPaid) {
        this.id = id;
        this.price = price;
        this.method = method;
        this.customerId = customerId;
        this.shipperId = shipperId;
        this.adminId = adminId;
        this.products = products;
        this.isPending = isPending;
        this.isPaid = isPaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
