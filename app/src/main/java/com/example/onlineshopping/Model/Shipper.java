package com.example.onlineshopping.Model;

import com.example.onlineshopping.Model.Enum.AccountType;

public class Shipper extends Employee {

    private String vehicleNumber;
    private String vehicleType;
    private int completedShipment;

    public void confirmPayment() {};

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getCompletedShipment() {
        return completedShipment;
    }

    public void setCompletedShipment(int completedShipment) {
        this.completedShipment = completedShipment;
    }

    public Shipper(AccountType type, String name, String username, String password, String position, String department, int salary, String vehicleNumber, String vehicleType, int completedShipment) {
        super(type, name, username, password, position, department, salary);
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.completedShipment = completedShipment;
    }
}
