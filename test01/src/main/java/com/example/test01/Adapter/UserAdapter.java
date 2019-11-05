package com.example.test01.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.example.test01.EditUser;
import com.example.test01.R;
import com.example.test01.entity.Person;
import com.example.test01.IndexOp.personManage;

import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static android.support.v4.content.ContextCompat.startActivity;

public class UserAdapter extends SimpleAdapter implements View.OnClickListener {
    Context context ;
    private List<Person> Userlist;
    personManage pm;
    View fview;
    public UserAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,List<Person> list,View fview) {
        super(context, data, resource, from, to);
        this.context = context;
        Userlist=list;
        this.fview=fview;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        Log.d(TAG, "getView: -----------------"+view);
        final Button BtnPerEdit=view.findViewById(R.id.btn_user_edit);
        BtnPerEdit.setTag(position);
        BtnPerEdit.setOnClickListener(this);
        final Button BtnPerDel=view.findViewById(R.id.btn_user_del);
        BtnPerDel.setTag(position);
        BtnPerDel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: ---------------"+view.getId()+"--------"+view.getTag());
        switch (view.getId()){
            case R.id.btn_user_del:
                Log.d(TAG, "onClick: 点击了btn_user_del的case------------"+Userlist.size());
                for (int i=0;i<Userlist.size();i++){
                    if (i==(Integer)view.getTag()){
                        Log.d(TAG, "onClick: 点击了btn_user_del的第"+i+"条------------"+Userlist.get(i).getName());
                        pm=new personManage();
                        pm.del(context,Userlist.get(i).getName());
                        pm.personManage(context,fview);
                    }
                }
                break;
            case R.id.btn_user_edit:
                for (int i=0;i<Userlist.size();i++){
                    if (i==(Integer)view.getTag()){
                        Log.d(TAG, "onClick: 点击了btn_user_edit的第"+i+"条------------");
                        Intent intent=new Intent(context,EditUser.class);
                        intent.putExtra("username",Userlist.get(i).getName());
                        startActivity(context,intent,null);
                        //pm.del(context,);
                    }
                }
                break;
        }
    }
}
