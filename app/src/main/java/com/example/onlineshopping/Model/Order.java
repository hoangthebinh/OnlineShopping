package com.example.onlineshopping.Model;

import com.example.onlineshopping.Model.Enum.PaymentMethod;

public class Order {
    private int price;
    private PaymentMethod method;
    private int customerId;
    private int shipperId;
    private int adminId;
    private int cartId;
    private int isPending;
    private int isPaid;

    public Order(int price, PaymentMethod method, int customerId, int shipperId, int adminId, int isPending, int isPaid) {
        this.price = price;
        this.method = method;
        this.customerId = customerId;
        this.shipperId = shipperId;
        this.adminId = adminId;
        this.isPending = isPending;
        this.isPaid = isPaid;
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

    public int getIsPending() {
        return isPending;
    }

    public void setIsPending(int isPending) {
        this.isPending = isPending;
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }
}
