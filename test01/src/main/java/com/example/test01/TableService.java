package com.example.test01;

import java.util.ArrayList;
import java.util.List;

public class TableService {
    static List<Table> tables=new ArrayList<Table>();
    static{
        for (int i=1;i<=20;i++){
            Table t=new Table();
            t.setId(i);
            t.setCode("Table"+i);
            t.setSeats(i%5*2+2);
            t.setCustomers(i%3==0?t.getSeats():0);
            t.setDescription("hh");
            tables.add(t);
        }
    }
    public List<Table> getTables(){
        return tables;
    }
}
