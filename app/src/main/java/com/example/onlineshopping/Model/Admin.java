package com.example.onlineshopping.Model;

import com.example.onlineshopping.Model.Enum.AccountType;
import com.example.onlineshopping.Resources.AccountManager;

public class Admin extends Employee {

    private int orderConfirmed;
    private int purchaseConfirmed;

    public int getOrderConfirmed() {
        return orderConfirmed;
    }

    public void setOrderConfirmed(int orderConfirmed) {
        this.orderConfirmed = orderConfirmed;
    }

    public int getPurchaseConfirmed() {
        return purchaseConfirmed;
    }

    public void setPurchaseConfirmed(int purchaseConfirmed) {
        this.purchaseConfirmed = purchaseConfirmed;
    }

    public Admin(AccountType type, String name, String username, String password, String position, String department, int salary, int orderConfirmed, int purchaseConfirmed) {
        super(type, name, username, password, position, department, salary);
        this.orderConfirmed = orderConfirmed;
        this.purchaseConfirmed = purchaseConfirmed;
    }

    public Admin(int id, AccountType type, String name, String username, String password, String position, String department, int salary, int orderConfirmed, int purchaseConfirmed) {
        super(id, type, name, username, password, position, department, salary);
        this.orderConfirmed = orderConfirmed;
        this.purchaseConfirmed = purchaseConfirmed;
    }

    public Admin() {
    }

    public void addProduct(String name, String kind, String group, String price) {
        AccountManager.getInstance().getDatabase().queryData("INSERT INTO Product VALUES(null, '"+ name +"', '"+ kind +"', '"+ group +"', " + price + ")");
    }

    public void deleteProduct() {};
    public void modifyProduct() {};
    public void makeShipment() {};
    public void confirmOrder() {};
    public void confirmPurchase() {};
}
