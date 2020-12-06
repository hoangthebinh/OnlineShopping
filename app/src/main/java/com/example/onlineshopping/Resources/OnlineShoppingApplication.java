package com.example.onlineshopping.Resources;

import android.app.Application;

public class OnlineShoppingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.setDatabase(new DatabaseHelper(this, "online_shopping.sqlite", null, 1));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DatabaseHelper.getDatabase().close();

    }

}
