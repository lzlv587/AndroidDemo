package com.example.test01.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;

import com.example.test01.R;
import com.example.test01.diancan;
import com.example.test01.entity.Food;

import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class FoodAdapter extends SimpleAdapter implements View.OnClickListener {
    Context context ;
    private List<Food> Foodlist;
    View fview;
    diancan dc;
    //private TextView number1;

    public FoodAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,List<Food> list,View fview) {
        super(context, data, resource, from, to);
        this.context = context;
        Foodlist=list;
        this.fview=fview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);


        //number1 = view.findViewById(R.id.count);
        //int nm= Integer.parseInt(number.getText().toString());
/*        btndel.setTag(position);
        btn.setOnClickListener(this);
        btndel.setOnClickListener(this);*/
        return view;

/*        final TextView food_ma_name=view.findViewById(R.id.food_ma_name);
        food_ma_name.setText("很强");*/

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: ---------------"+view.getId()+"--------點擊放發"+view.getTag());
        switch (view.getId()){
            case R.id.btnadd:
                Log.d(TAG, "onClick: ---------------"+view.getId()+"--------點擊放發2222"+view.getTag());
                for (int i=0;i<Foodlist.size();i++){
                    Log.d(TAG, "onClick: 点击了add的第"+i+"条啊啊啊aaa ------------");
                    if (i==(Integer)view.getTag()){
/*                        Log.d(TAG, "onClick: 点击了add的第"+i+"条啊啊啊bbb------------");
                        int nm= Integer.parseInt(number1.getText().toString());
                        int result=nm+1;
                        Log.d(TAG, "onClick: 点击了add的第"+i+"条啊啊啊ccc------------"+nm+"---"+result);

                        number1.setText(result);
                        dc=new diancan();
                        dc.dian(context,fview);*/
/*                        dc=new diancan();
                        dc.dian(context,view);*/

                    }
                }
                break;

        }
    }
}
