package com.example.test01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.test01.entity.Food;

public class EditFood extends AppCompatActivity {
    private EditText etFoodName;
    private EditText etFoodPrice;
    private EditText etFoodNote;
    private String kind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
        Intent intent=getIntent();
        final String data=intent.getStringExtra("foodname");
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
        etFoodName.setText(data);
        Button btn=findViewById(R.id.btn_edit_food);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etFoodName.getText().toString();
                String price=etFoodPrice.getText().toString();
                String note=etFoodNote.getText().toString();
                Food food=new Food();
                food.setKind(kind);
                food.setDes(note);
                food.setPrice(Double.parseDouble(price));
                food.setName(name);
                food.save();
                Toast.makeText(EditFood.this,"修改成功",Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(EditFood.this,index.class);
                startActivity(intent1);
            }
        });
    }
}
