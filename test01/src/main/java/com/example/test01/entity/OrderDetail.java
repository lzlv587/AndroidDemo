package com.example.test01.entity;

import org.litepal.crud.LitePalSupport;

public class OrderDetail extends LitePalSupport {
    String foodname;
    int count;
    String kind;
    int order_id;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "菜名='" + foodname + '\'' +
                ", 数量=" + count +
                ", 种类='" + kind + '\'' +
                ", 订单id=" + order_id +
                '}';
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
