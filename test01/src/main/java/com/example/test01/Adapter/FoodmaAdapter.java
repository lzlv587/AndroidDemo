package com.example.test01.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.test01.EditFood;
import com.example.test01.IndexOp.FoodManager;
import com.example.test01.R;
import com.example.test01.entity.Food;

import org.litepal.LitePal;

import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static android.support.v4.content.ContextCompat.startActivity;

public class FoodmaAdapter extends SimpleAdapter implements View.OnClickListener {
    Context context ;
    private List<Food> Foodlist;
    FoodManager fma;
    View fview;
    public FoodmaAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,List<Food> list,View fview) {
        super(context, data, resource, from, to);
        this.context = context;
        Foodlist=list;
        this.fview=fview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {
        View view = super.getView(position, convertView, parent);
        Log.d(TAG, "getView: -----------------"+view);
        final Button btn_food_ma_edit=view.findViewById(R.id.btn_food_ma_edit);
        btn_food_ma_edit.setTag(position);
        btn_food_ma_edit.setOnClickListener(this);
        final Button btn_food_ma_del=view.findViewById(R.id.btn_food_ma_del);
        btn_food_ma_del.setOnClickListener(this);
        btn_food_ma_del.setTag(position);
        return view;

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: ---------------"+view.getId()+"--------"+view.getTag());
        switch (view.getId()){
            case R.id.btn_food_ma_edit:
                for (int i=0;i<Foodlist.size();i++){
                    if (i==(Integer)view.getTag()){
                        Log.d(TAG, "onClick: 点击了btn_user_edit的第"+i+"条------------");
                        Intent intent=new Intent(context,EditFood.class);
                        intent.putExtra("foodname",Foodlist.get(i).getName());
                        startActivity(context,intent,null);
                        //pm.del(context,);
                    }
                }
                break;
            case R.id.btn_food_ma_del:
                for (int i=0;i<Foodlist.size();i++){
                    if (i==(Integer)view.getTag()){
                        Log.d(TAG, "onClick: 点击了btn_user_edit的第"+i+"条------------");

                        LitePal.deleteAll(Food.class,"name=?",Foodlist.get(i).getName());
                        Toast.makeText(context,"刪除成功",Toast.LENGTH_SHORT).show();
                        //pm.del(context,);
                        fma=new FoodManager();
                        fma.foodManage(context,fview);
                    }
                }
                break;
        }
    }
}
