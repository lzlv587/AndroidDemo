package com.example.test01.IndexOp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test01.R;
import com.example.test01.entity.Food;
import com.example.test01.entity.OrderDetail;
import com.example.test01.entity.Orders;

import org.litepal.LitePal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class liushui extends AppCompatActivity {
    TextView tv_startdate = null;
    TextView tv_enddate = null;
    int year = 2016;
    int month = 10;
    int day = 8;
    int houre = 15;
    int minute = 20;
    private List<Orders> OrderList=new ArrayList<>();
    private String show;
    private TextView dialog_tv_date;
    private TextView dialog_tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liushui);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        //初始日期
        dialog_tv_date = findViewById(R.id.dialog_tv_date);
        //结束日期
        dialog_tv_time = findViewById(R.id.dialog_tv_time);
        final EditText et_num_query_order=findViewById(R.id.et_num_query_order);
        Button btn_query_order=findViewById(R.id.btn_query_order);
        Button btn_num_query_order=findViewById(R.id.btn_num_query_order);
        final TextView ordershowTexiview=findViewById(R.id.ordershowTexiview);
        show = "订单";
        btn_num_query_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<OrderDetail> orderDetail=LitePal.select().where("order_id=?",et_num_query_order.getText().toString()).find(OrderDetail.class);
                List<Orders> orders=LitePal.select().where("id=?",et_num_query_order.getText().toString()).find(Orders.class);
                Log.d(TAG, "onClick: ---------------------ordersize"+orderDetail.size()+"---"+orders.size());
                for (int i=0;i<orderDetail.size();i++){
                    show = show +orderDetail.get(i).toString();
                }
                show=show+orders.get(0).toString();
                ordershowTexiview.setText(show);
                show=null;
            }
        });


        btn_query_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OrderList=LitePal.select("foodList").where("SaleDate >= ? and SaleDate <= ?","","").find(Order.class);
                //Cursor cursor =LitePal.findBySQL("select * from Orders where SaleDay >= ? and SaleDay <= ?",tv_startdate.getText().toString(),tv_enddate.getText().toString());
               /* Cursor cursor =LitePal.findBySQL("select * from Orders where Tabless=?","桌子1");
                if (cursor.moveToFirst()){
                    do {
                        int num=cursor.getInt(cursor.getColumnIndex("summ"));

                        Log.d(TAG, "onClick: ----------------------总价："+num);
                        //Log.d(TAG, "onClick: ----------------------日期："+date);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                OrderList=LitePal.findAll(Orders.class);
                List<OrderDetail> orderDetail=LitePal.findAll(OrderDetail.class);
                for (int i=0;i<OrderList.size();i++){
                    Log.d(TAG, "onClick:-------------------------- "+OrderList.get(i).toString());

                }
                for (int i=0;i<orderDetail.size();i++){

                    Log.d(TAG, "onClick:--------------------------订单信息： "+orderDetail.get(i).toString());
                }*/

               //实现用日期查询订单
                String startdate=dialog_tv_date.getText().toString();
                String enddate=dialog_tv_time.getText().toString();
                List<Orders> OrderList=LitePal.select().where("saleDay >= ? and saleDay <= ?",startdate,enddate).find(Orders.class);
                for (int i=0;i<OrderList.size();i++){
                    Log.d(TAG, "onClick: -------------------------"+OrderList.size());
                    List<OrderDetail> orderDetails=LitePal.select().where("order_id=?",OrderList.get(i).getId()+"").find(OrderDetail.class);
                    for (int j=0;j<orderDetails.size();j++){
                        show=show+orderDetails.get(j).toString();
                    }
                    show=show+OrderList.get(i).toString()+"/////////////////////////";
                }
                ordershowTexiview.setText(show);


            }
        });
    }
    private void initView() {
        tv_startdate = (TextView) findViewById(R.id.dialog_tv_date);
        tv_enddate = (TextView) findViewById(R.id.dialog_tv_time);
    }

    // 点击事件,湖区日期
    public void getDate(View v) {

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                liushui.this.year = year;
                month = monthOfYear;
                day = dayOfMonth;
                showDate();
            }
        }, 2019, 6, 25).show();

    }

    // 点击事件,湖区日期
    public void getTime(View v) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                liushui.this.year = year;
                month = monthOfYear;
                day = dayOfMonth;
                showTime();
            }
        }, 2016, 10, 8).show();

        /*new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                houre = hourOfDay;
                liushui.this.minute = minute;
            }
        }, 15, 20, true).show();
        showTime();*/
    }

    // 显示选择日期
    private void showDate() {
        tv_startdate.setText(year + "-" + month + "-" + day);
    }

    // 显示选择日期
    private void showTime() {
        tv_enddate.setText( year + "-" + month + "-" + day );
        /*tv_enddate.setText("你选择的时间是：" + houre + "时" + minute + "分");*/
    }



}
