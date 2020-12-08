package com.example.onlineshopping.Resources;

import android.database.Cursor;
import android.util.Log;

import com.example.onlineshopping.Model.Account;
import com.example.onlineshopping.Model.Admin;
import com.example.onlineshopping.Model.Customer;
import com.example.onlineshopping.Model.Enum.AccountType;

import java.util.ArrayList;

public class AccountManager {

    private static AccountManager instance = new AccountManager();

    public static AccountManager getInstance() {
        return instance;
    }

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    DatabaseHelper database;

    public AccountManager() {
        this.database = DatabaseHelper.getDatabase();
    }

    public DatabaseHelper getDatabase() {
        return database;
    }

    public boolean login(String username, String password) {
        boolean loggedIn = false;

        Cursor accData = database.getData("SELECT * FROM Account WHERE username = '" + username + "' AND password = '" + password + "'");
        if (accData.moveToFirst()) {
            loggedIn = true;
            Log.d("login", "login: account matched");
            int ownerId = accData.getInt(0);
            String type = accData.getString(1);
            String accUsername = accData.getString(2);
            String accPassword = accData.getString(3);

            if (type.equals("admin")) {
                Cursor data = database.getData("SELECT * FROM Admin WHERE id = " + ownerId);
                Admin admin = new Admin();
                if (data.moveToFirst()) {
                    admin.setId(data.getInt(0));
                    admin.setType(AccountType.ADMIN);
                    admin.setUsername(accUsername);
                    admin.setPassword(accPassword);
                    admin.setName(data.getString(1));
                    admin.setPosition(data.getString(2));
                    admin.setDepartment(data.getString(3));
                    admin.setSalary(data.getInt(4));
                    admin.setOrderConfirmed(data.getInt(5));
                    admin.setPurchaseConfirmed(data.getInt(6));
                }
                setAccount(admin);
            }
            else if (type.equals("customer")) {
                Cursor data = database.getData("SELECT * FROM Customer WHERE id = " + ownerId);
                Customer customer = new Customer();
                if (data.moveToFirst()) {
                    customer.setId(data.getInt(0));
                    customer.setType(AccountType.CUSTOMER);
                    customer.setName(data.getString(1));
                    customer.setUsername(accUsername);
                    customer.setPassword(accPassword);
                    customer.setAddress(data.getString(2));
                    customer.setPhoneNumber(data.getString(3));
                }
                setAccount(customer);
            }
            else if (type.equals("shipper")) {
            }
        }
        return loggedIn;
    }

    public void registerNewCustomer(String username, String password, String name, String address, String phone) {
        database.queryData("INSERT INTO Customer VALUES(null, '"+name+"', '"+address+"', "+phone+")");
        Cursor data = database.getData("SELECT id FROM Customer WHERE id = (SELECT MAX(id) FROM Customer)");
        if (data.moveToFirst()) {
            int customerId = data.getInt(0);
            database.queryData("INSERT INTO Account VALUES("+customerId+", 'customer', '"+username+"', '"+password+"')");
            login(username, password);
        }
    }


    private boolean checkExistedUsername(String username) {
        Cursor modelData = database.getData("SELECT * FROM Account WHERE username = '" + username + "'");
        while (modelData.moveToNext()) {
            return true;
        }
        return false;
    }

}
