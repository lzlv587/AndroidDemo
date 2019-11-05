package com.example.test01;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.test01.Adapter.FoodAdapter;
import com.example.test01.entity.Food;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class diancan extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    private List<Food> foodList=new ArrayList<>();
    private void intFood(){
        for (int i=0;i<10;i++){
/*            Food apple=new Food("apple",20,100);
            foodList.add(apple);
            Food banana=new Food("banana",20,30);
            foodList.add(banana);*/
        }
    }
    private void findALl(){
        foodList=LitePal.findAll(Food.class);
    }
    private ArrayList<Map<String,Object>> getData(){
        Log.d(TAG, "getData: 进入了getdata方法--------------------------");
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i=0;i<foodList.size();i++){
            map = new HashMap<String, Object>();
            map.put("image", R.drawable.banana);
            map.put("name", foodList.get(i).getName());
            map.put("price",foodList.get(i).getPrice());
            data.add(map);
        }
/*        map = new HashMap<String, Object>();
        //map.put("image", R.drawable.log2);
        map.put("name", "鱼香肉丝");
        map.put("add",R.drawable.crying);
        map.put("del", R.drawable.smail);//此处修改图片按钮图片
        data.add(map);
        map = new HashMap<String, Object>();
        //map.put("image", R.drawable.log2);
        map.put("name", "拔丝地瓜");
        map.put("add",R.drawable.crying);
        //map.put("size", "30dp");
        map.put("del", R.drawable.crying);
        data.add(map);*/
        Log.d(TAG, "getData: getdata方法结束--------------------------");
        return data;
    }
    public void dian(Context context,View view) {
        findALl();

        Log.d(TAG, "getData:进入点餐方法s2--------------------------"+view);
        ListView listView =view.findViewById(R.id.List_viewshow);
        Log.d(TAG, "getData:进入点餐方法s3--------------------------");
        FoodAdapter adapter=new FoodAdapter(context,getData(),R.layout.food_item,new String[]{"image","name","price"},
                new int[]{ R.id.foodimg, R.id.food_name,R.id.foodprice},foodList,view);
        listView.setAdapter(adapter);
        Log.d(TAG, "getData: setAdapter方法结束--------------------------");
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(index.this,"点击了事件 i= "+i,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
