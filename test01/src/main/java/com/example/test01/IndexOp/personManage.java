package com.example.test01.IndexOp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test01.Adapter.MydatabaseHelper;
import com.example.test01.Adapter.UserAdapter;
import com.example.test01.R;
import com.example.test01.entity.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class personManage extends AppCompatActivity{
    private MydatabaseHelper dbHelper;
    private List<Person> personList=new ArrayList<>();



    public void personManage(Context context, View view){
        findAll(context);
        ListView listView =view.findViewById(R.id.List_viewshow);
        UserAdapter adapter=new UserAdapter(context,getData(),R.layout.personmanager_item,new String[]{"name","edit","del"},
                new int[]{ R.id.person_name, R.id.btn_user_edit,R.id.btn_user_del},personList,view);
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
        for(int i=0;i<personList.size();i++){
            map = new HashMap<String, Object>();
            //map.put("image", R.drawable.log2);

            map.put("name",personList.get(i).getName());
            map.put("edit","修改");
            map.put("del", "刪除");//此处修改图片按钮图片
            data.add(map);
        }
        return data;
    }
    public void del(Context context,String name){
        dbHelper=new MydatabaseHelper(context,"User.db",null,2);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete("User","name=?",new String[]{name});
        Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
    }
    public void findAll(Context context){
        dbHelper=new MydatabaseHelper(context,"User.db",null,2);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("User",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String password=cursor.getString(cursor.getColumnIndex("password"));
                String id=cursor.getString(cursor.getColumnIndex("id"));
                Log.d(TAG, "findAll: --------------------------id"+id);
                Log.d(TAG, "findAll: --------------------------name"+name);
                Log.d(TAG, "findAll: --------------------------password"+password);
                Person person=new Person();
                person.setId(Integer.parseInt(id));
                person.setName(name);
                person.setPassword(password);
                personList.add(person);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
}
