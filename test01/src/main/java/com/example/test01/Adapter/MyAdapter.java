package com.example.test01.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.test01.R;
import com.example.test01.entity.Food;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private int resourceId;
    public MyAdapter(@NonNull Context context, int resource, @NonNull List<Food> objects) {
        //super(context, resource, objects);
        resourceId=resource;
    }
    public MyAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Food food= (Food) getItem(i);
        view=LayoutInflater.from(mContext).inflate(resourceId,null,false);
        TextView foodName=view.findViewById(R.id.food_name);
        foodName.setText(food.getName());



        ViewHolder viewHolder=null;
        if (view==null){
            viewHolder=new ViewHolder();
            view =LayoutInflater.from(mContext).inflate(i,null,false);

            //viewHolder.mTextView=view.findViewById(R.id.count);
            view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TextView count=view.findViewById(R.id.count);
                //count.setText(Integer.parseInt(count.getText().toString())+1);
               // viewHolder.mTextView.setText(Integer.parseInt(viewHolder.mTextView.getText().toString())+1);
            }
        });
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
    class ViewHolder {
        TextView mTextView;
        ImageButton mButtonAdd;
        ImageButton mButtonDel;
    }
}
