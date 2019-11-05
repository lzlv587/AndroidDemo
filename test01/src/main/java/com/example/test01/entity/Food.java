package com.example.test01.entity;

import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.ArrayList;
import java.util.List;

public class Food extends LitePalSupport {
    private int id;
    private String name;
    private double price;
    private String des;
    private String kind;

    @Override
    public String toString() {
        return "Food{"+"'"
                +name + '\'' +
                ", price=" + price +
                ", kind='" + kind + '\'' +
                '}';
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
