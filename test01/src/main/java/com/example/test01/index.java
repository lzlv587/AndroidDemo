package com.example.test01;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test01.Adapter.MydatabaseHelper;
import com.example.test01.IndexOp.FoodManager;
import com.example.test01.IndexOp.daincai;
import com.example.test01.IndexOp.liushui;
import com.example.test01.IndexOp.personManage;
import com.example.test01.entity.Food;

import java.util.ArrayList;
import java.util.List;

public class index extends AppCompatActivity{
    private static final String TAG = "index";
    private List<Food> foodList=new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    private Button btnadd;
    diancan dian;
    private FoodManager fm;
    private MydatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnadd=findViewById(R.id.btnadd);
        mListView=findViewById(R.id.List_viewshow);
        mListView.setVisibility(mListView.VISIBLE);
        dian=new diancan();
        dian.dian(index.this,getWindow().getDecorView());
        mDrawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navView=findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.info);
        }
        navView.setCheckedItem(R.id.nav_diancan);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {



            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               // Toast.makeText(index.this,item+"",Toast.LENGTH_SHORT).show();
                switch (item.toString()){
                    case "点餐":

                        Toast.makeText(index.this,"点击了点餐按钮",Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(index.this,daincai.class);
                        startActivity(intent);
                        break;
                    case "人员管理":
                        btnadd.setVisibility(btnadd.VISIBLE);
                        btnadd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(index.this,"点击了人员添加按钮",Toast.LENGTH_SHORT).show();
                            }
                        });
                        mListView.setVisibility(mListView.VISIBLE);
                        Toast.makeText(index.this,"点击了人员管理按钮",Toast.LENGTH_SHORT).show();
                        personmanage();
                        break;
                    case "菜品管理":
                        btnadd.setVisibility(btnadd.VISIBLE);
                        btnadd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(index.this,"点击了菜品添加按钮",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(index.this,AddFood.class);
                                startActivity(intent);
                            }
                        });
                       // Toast.makeText(index.this,"点击了菜品管理按钮",Toast.LENGTH_SHORT).show();
                        mListView.setVisibility(mListView.VISIBLE);
                        //foodmanager();
                        fm=new FoodManager();
                        fm.foodManage(index.this,getWindow().getDecorView());
                        break;
                    case "订单管理":

                        Intent intent1=new Intent(index.this,liushui.class);
                        startActivity(intent1);
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void foodmanager() {
/*        LitePal.getDatabase();
        Toast.makeText(index.this,"创建成功",Toast.LENGTH_SHORT).show();
        Food food=new Food();
        food.setName("麻辣鱼鳞");
        food.save();
        Toast.makeText(index.this,"添加成功",Toast.LENGTH_SHORT).show();*/

    }

    private void personmanage() {
        personManage personManage=new personManage();

        personManage.personManage(index.this,getWindow().getDecorView());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
