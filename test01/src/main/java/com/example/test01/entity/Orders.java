package com.example.test01.entity;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders extends LitePalSupport {
    private int id;
    private String tabless;
    private int customers;
    private List<OrderDetail> details = new ArrayList<OrderDetail>();
    private String dess;
    private Date saleDay;
    private int summ;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", 桌子='" + tabless + '\'' +
                ", 顾客数=" + customers +
                ", 备注='" + dess + '\'' +
                ", 日期=" + saleDay +
                ", 总额=" + summ +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTabless() {
        return tabless;
    }

    public void setTabless(String tabless) {
        this.tabless = tabless;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }

    public String getDess() {
        return dess;
    }

    public void setDess(String dess) {
        this.dess = dess;
    }

    public Date getSaleDay() {
        return saleDay;
    }

    public void setSaleDay(Date saleDay) {
        this.saleDay = saleDay;
    }

    public int getSumm() {
        return summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    //查找订单对应的订单详情
    public List<OrderDetail> getDetailLists(){
        return LitePal.where("order_id=?",String.valueOf(id)).find(OrderDetail.class);
    }
}
