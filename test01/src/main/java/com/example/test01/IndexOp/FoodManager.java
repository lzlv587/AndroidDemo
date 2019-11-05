package com.example.test01.IndexOp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.test01.Adapter.FoodmaAdapter;
import com.example.test01.R;
import com.example.test01.entity.Food;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FoodManager {

    private List<Food> foodList;

    public void foodManage(Context context, View view){
        foodList = LitePal.findAll(Food.class);
        ListView listView =view.findViewById(R.id.List_viewshow);
        Log.d(TAG, "getData: setAdapter方法开始--------------------------"+getData());
        FoodmaAdapter adapter=new FoodmaAdapter(context,getData(),R.layout.foodma_item,new String[]{"image","name","price","edit","del"},
                new int[]{ R.id.mafoodimg,R.id.food_ma_name, R.id.maprice,R.id.btn_food_ma_edit,R.id.btn_food_ma_del},foodList,view);
        listView.setAdapter(adapter);
        Log.d(TAG, "getData: setAdapter方法结束--------------------------"+getData().size());
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(index.this,"点击了事件 i= "+i,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayList<Map<String,Object>> getData(){
        Log.d(TAG, "getData: 进入了getdata方法--------------------------");
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i=0;i<foodList.size();i++){
            map = new HashMap<String, Object>();
            //map.put("image", R.drawable.log2);
            map.put("image",R.drawable.banana);
            map.put("name",foodList.get(i).getName());
            map.put("price",foodList.get(i).getPrice());
            map.put("edit","修改");
            map.put("del", "刪除");//此处修改图片按钮图片
            data.add(map);
        }
        return data;
    }
}
