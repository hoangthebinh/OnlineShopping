package com.example.onlineshopping.Resources;

import android.database.Cursor;
import android.util.Log;

import com.example.onlineshopping.Model.Account;
import com.example.onlineshopping.Model.Admin;
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


            switch (type) {
                case "admin":
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
                    break;
                case "shipper":
                    break;
                case "customer":
                    break;
            }
        }
        return loggedIn;
    }


    private boolean checkExistedUsername(String username) {
        Cursor modelData = database.getData("SELECT * FROM Account WHERE username = '" + username + "'");
        while (modelData.moveToNext()) {
            return true;
        }
        return false;
    }

}
