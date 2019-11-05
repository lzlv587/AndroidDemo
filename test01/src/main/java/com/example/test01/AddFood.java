package com.example.test01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.test01.entity.Food;

public class AddFood extends AppCompatActivity {

    private static final String TAG = "AddFood";
    private EditText etFoodName;
    private EditText etFoodPrice;
    private EditText etFoodNote;
    private String kind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        final String[] ctype = new String[]{"凉菜", "热菜", "汤菜", "主食", "酒水"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner=findViewById(R.id.sp_food_kinds);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item=ctype[i];
                //Log.d(TAG, "onItemSelected: test+-----------------------------0"+item);
                kind =item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        etFoodName = findViewById(R.id.tv_food_name);
        etFoodPrice = findViewById(R.id.tv_food_price);
        etFoodNote = findViewById(R.id.tv_food_note);


        Button btnadd=findViewById(R.id.btn_add_food);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etFoodName.getText().toString();
                String note = etFoodNote.getText().toString();
                String price = etFoodPrice.getText().toString();
                Log.d(TAG, "onCreate: -----------------------"+ kind+"名："+name+"價格："+price+"筆記"+note);

                Food food=new Food();
                food.setName(name);

                food.setPrice(Double.parseDouble(price));
                food.setDes(note);
                food.setKind(kind);

                food.save();

                Toast.makeText(AddFood.this,"添加成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddFood.this,index.class);
                startActivity(intent);
            }
        });
    }
}
