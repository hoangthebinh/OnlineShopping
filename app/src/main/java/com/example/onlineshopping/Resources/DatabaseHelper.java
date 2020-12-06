package com.example.onlineshopping.Resources;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.onlineshopping.Model.Enum.PaymentMethod;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper database;

    public static DatabaseHelper getDatabase() {
        return database;
    }

    public static void setDatabase(DatabaseHelper database) {
        DatabaseHelper.database = database;
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: Start creating data
        isCreating = true;
        currentDB = db;

        //TODO: Create tables

        queryData("CREATE TABLE IF NOT EXISTS Account(" +
                "ownerId INTEGER NOT NULL," +
                " type VARCHAR(255)," +
                " username VARCHAR(255) NOT NULL," +
                " password VARCHAR(255) NOT NULL)");

        queryData("CREATE TABLE IF NOT EXISTS Admin(" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                " name VARCHAR(255) NOT NULL," +
                " position VARCHAR(255)," +
                " department VARCHAR(255)," +
                " salary INTEGER," +
                " orderConfirmed INTEGER," +
                " purchaseConfirmed INTEGER)");

        queryData("CREATE TABLE IF NOT EXISTS Shipper(" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                " name VARCHAR(255) NOT NULL," +
                " position VARCHAR(255)," +
                " department VARCHAR(255)," +
                " salary INTEGER," +
                " vehicleNumber VARCHAR(255)," +
                " vehicleType VARCHAR(255)," +
                " completedShipment INTEGER)");
//
//        queryData("CREATE TABLE IF NOT EXISTS Customer(" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                " name VARCHAR(255) NOT NULL," +
//                " address VARCHAR(255)," +
//                " phoneNumber INTEGER)");
//
        queryData("CREATE TABLE IF NOT EXISTS Product(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name VARCHAR(255) NOT NULL," +
                " kind VARCHAR(255) NOT NULL," +
                " fromGroup VARCHAR(255)," +
                " price INTEGER)");

//        queryData("CREATE TABLE IF NOT EXISTS Order(" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                " price VARCHAR(255) NOT NULL," +
//                " method VARCHAR(255) NOT NULL," +
//                " customerId INTEGER," +
//                " adminId INTEGER," +
//                " cartId INTEGER," +
//                " isPending INTEGER," +
//                " isPaid INTEGER)");
//
//        queryData("CREATE TABLE IF NOT EXISTS Cart(" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                " products TEXT)");

        //TODO: Insert Chapter data
        queryData("INSERT INTO Account VALUES(1, 'admin', 'admin1', 'admin1')");
        queryData("INSERT INTO Account VALUES(1, 'shipper', 'shipper1', 'shipper1')");
//
        queryData("INSERT INTO Admin VALUES(1, 'Hoàng Thế Bình', 'Kiểm đơn', 'Quản lý', 8000000, 0, 0)");
        queryData("INSERT INTO Shipper VALUES(1, 'Lê Minh Hiếu', 'Giao hàng', 'Vận chuyển', 5000000, '29-D2-09900', 'Wave trắng 2017', 0)");



        //TODO: Stop creating data
        isCreating = false;
        currentDB = null;
    }

    boolean isCreating = false;
    SQLiteDatabase currentDB = null;

    @Override
    public SQLiteDatabase getWritableDatabase() {
        // TODO Auto-generated method stub
        if(isCreating && currentDB != null){
            return currentDB;
        }
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        // TODO Auto-generated method stub
        if(isCreating && currentDB != null){
            return currentDB;
        }
        return super.getReadableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
