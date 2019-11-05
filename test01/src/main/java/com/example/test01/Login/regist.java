package com.example.test01.Login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test01.Adapter.MydatabaseHelper;
import com.example.test01.R;

public class regist extends AppCompatActivity {
    private MydatabaseHelper dbHelper;
    private EditText name;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        name = findViewById(R.id.idtext);
        pwd = findViewById(R.id.pwdtext);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button=findViewById(R.id.registbtn);
        dbHelper=new MydatabaseHelper(this,"User.db",null,2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(regist.this, "test", Toast.LENGTH_SHORT).show();
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                //Toast.makeText(regist.this, "test2", Toast.LENGTH_SHORT).show();
                ContentValues values=new ContentValues();
                String sName= name.getText().toString();
                String sPwd=pwd.getText().toString();
                values.put("name",sName);
                values.put("password",sPwd);
                db.insert("User",null,values);
                values.clear();
                Toast.makeText(regist.this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(regist.this,login.class);
                startActivity(intent);
            }
        });
    }

}
