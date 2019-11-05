package com.example.test01;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test01.Adapter.MydatabaseHelper;
import com.example.test01.Login.login;

public class EditUser extends AppCompatActivity {
    private EditText pwd;
    private static final String TAG = "EditUser";
    private MydatabaseHelper dbHelper;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Intent intent=getIntent();
        final String data=intent.getStringExtra("username");
        Log.d(TAG, "onCreate: -------------------"+data);
        username = findViewById(R.id.idtext);
        username.setText(data);
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
                String sName= username.getText().toString();
                String sPwd=pwd.getText().toString();
                values.put("name",sName);
                values.put("password",sPwd);
                db.update("User",values,"name=?",new String[]{data});
                values.clear();
                Toast.makeText(EditUser.this,"修改成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(EditUser.this,login.class);
                startActivity(intent);
            }
        });
    }
}
