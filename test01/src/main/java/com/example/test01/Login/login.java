package com.example.test01.Login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test01.Adapter.MydatabaseHelper;
import com.example.test01.R;
import com.example.test01.index;

public class login extends AppCompatActivity {

    private String TAG="login";
    private MydatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper=new MydatabaseHelper(this,"User.db",null,2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final TextView tx1=findViewById(R.id.idtext);
        final TextView tx2=findViewById(R.id.pwdtext);

        Button btnlogin=findViewById(R.id.loginbtn);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=tx1.getText().toString();
                String pwd=tx2.getText().toString();
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                String[] selectionArgs = { id,pwd };

                Cursor cursor=db.query("User",null,"name=? and password=?",selectionArgs,null,null,null);
                Log.d(TAG, "onClick: -----------------------"+id);
                Log.d(TAG, "onClick: -----------------------"+pwd);
                Log.d(TAG, "onClick: -----------------------"+cursor);
                Log.d(TAG, "onClick: -----------------------"+cursor.getCount());
                if (cursor.getCount()!=0){
                    Intent intent=new Intent(login.this,index.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
