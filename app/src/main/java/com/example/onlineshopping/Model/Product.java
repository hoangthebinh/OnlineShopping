package com.example.onlineshopping.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String kind;
    private String group;
    private int price;

    public Product() {
    }

    public Product(int id, String name, String kind, String group, int price) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.group = group;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

