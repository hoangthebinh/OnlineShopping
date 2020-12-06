package com.example.onlineshopping.Model;

import com.example.onlineshopping.Model.Enum.AccountType;

import java.util.ArrayList;
import java.util.OptionalDouble;

public abstract class Employee extends Account {
    private String position;
    private String department;
    private int salary;

    public Employee() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee(AccountType type, String name, String username, String password, String position, String department, int salary) {
        super(type, name, username, password);
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    public Employee(int id, AccountType type, String name, String username, String password, String position, String department, int salary) {
        super(id, type, name, username, password);
        this.position = position;
        this.department = department;
        this.salary = salary;
    }
}
