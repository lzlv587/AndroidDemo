package com.example.test01.IndexOp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test01.Adapter.OrderAdapter;
import com.example.test01.R;
import com.example.test01.entity.Food;
import com.example.test01.entity.OrderDetail;
import com.example.test01.entity.Orders;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class daincai extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "11";
    private EditText customersEdt;
    private View foodView;
    private TextView sumTxv;
    private List<Food> foodList;
    private List<Integer> nums;
    private Spinner foodSpn;
    private EditText desEdt;
    private String[] foodTypes;
    private EditText numEdt;
    private ListView orderedLtv;
    private Spinner tableSpn;
    private List<Map<String,Object>> orderedList=new ArrayList<Map<String,Object>>();
    private String[] orderedLtvKeys=new String[]{"no","name","description","num","price","checked"};
    private int[] orderedLtvIds=new int[]{
            R.id.noTxv,R.id.nameTxv,R.id.descriptionTxv,R.id.numTxv,R.id.priceTxv,R.id.checkCkb
    };
    private LayoutInflater li;
    private View orderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daincai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        customersEdt=findViewById(R.id.customersEdt);
        sumTxv=findViewById(R.id.sumTxv);
        tableSpn=findViewById(R.id.tableSpn);
        desEdt=findViewById(R.id.descriptionEdt);
        //orderView=findViewById(R.id.OrderView);
        li=LayoutInflater.from(this);
        foodView = li.inflate(R.layout.order_dialog,null);

        foodList=new ArrayList<>();
        nums=new ArrayList<>();

        final String[] ctype = new String[]{"桌子1", "桌子2", "桌子3", "桌子4", "桌子5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.tableSpn);
        spinner.setAdapter(adapter);

        Button btndiancai=findViewById(R.id.addFoodBtn);
        btndiancai.setOnClickListener(this);
        Button btncencel=findViewById(R.id.cancelBtn);
        btncencel.setOnClickListener(this);
        Button btnorder=findViewById(R.id.orderBtn);
        btnorder.setOnClickListener(this);

/*        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item=ctype[i];
                //Log.d(TAG, "onItemSelected: test+-----------------------------0"+item);
                kind =item;
            }
    }*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addFoodBtn:
                Log.d(TAG, "onClick: -------------------------点击了点菜按钮");


                li = LayoutInflater.from(this);
                foodView= li.inflate(R.layout.order_dialog,null);
                GridView foodtypeGdv=foodView.findViewById(R.id.foodTypeGdv);
                numEdt=foodView.findViewById(R.id.numEdt);
                initFoodTypeGv(foodtypeGdv);


                foodSpn = foodView.findViewById(R.id.foodSpn);
                Log.d(TAG, "initFoodTypeGv: -------------test3");
                //final String[] foodtypes = new String[]{"凉菜", "热菜", "汤菜", "主食", "酒水"};
                List<Food> foods=LitePal.findAll(Food.class);
                Log.d(TAG, "initFoodTypeGv: -------------test4");
                ArrayAdapter<Food> arrayAdapter = new ArrayAdapter<Food>(this, R.layout.support_simple_spinner_dropdown_item, foods);
                foodSpn.setAdapter(arrayAdapter);


                Log.d(TAG, "initFoodTypeGv: -------------test5");
                AlertDialog.Builder dia=new AlertDialog.Builder(daincai.this);
                        dia.setView(foodView)
                        .setTitle("选择酒菜")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                orderedLtv=findViewById(R.id.orderedLtv);
                                OrderAdapter orderAdapter=new OrderAdapter(daincai.this,getData(),R.layout.order,orderedLtvKeys,
                                        orderedLtvIds);
                                orderedLtv.setAdapter(orderAdapter);

                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
                Log.d(TAG, "initFoodTypeGv: -------------test6");
                break;
            case R.id.orderBtn:
                //下单功能
                int customers=0;
                String c=customersEdt.getText().toString();
                if (c.length()>0){
                    customers=Integer.parseInt(c);
                    Log.d(TAG, "onClick: ------------------顾客人数"+customers);
                    Date date = new Date(System.currentTimeMillis());
                    String des=desEdt.getText().toString();
                    String table= tableSpn.getSelectedItem().toString();
                    int sum= Integer.parseInt(sumTxv.getText().toString());
                    Orders order=new Orders();
                    order.setTabless(table);
                    order.setCustomers(customers);
                    order.setDess(des);
                    order.setSaleDay(date);
                    order.setSumm(sum);
                    order.save();
                    for (int i=0;i<foodList.size();i++){
                        Log.d(TAG, "onClick: ------------------订单组："+foodList.get(i).toString());
                        OrderDetail orderDetail=new OrderDetail();
                        orderDetail.setFoodname(foodList.get(i).getName());
                        orderDetail.setCount(1);
                        orderDetail.setKind(foodList.get(i).getKind());
                        orderDetail.setOrder_id(order.getId());
                        //order.getFoodList().add(foodList.get(i));
                        Log.d(TAG, "onClick: ----------------------order.getid"+order.getId());

                        order.getDetails().add(orderDetail);
                        orderDetail.save();

                    }
                    order.save();

                    Toast.makeText(this,"保存订单成功",Toast.LENGTH_SHORT).show();
                    foodList.clear();
                    orderedLtv.setVisibility(View.GONE);
                    sumTxv.setText(0+"");
                }
                if (customers<=0){
                    new AlertDialog.Builder(this).setTitle("提示").setMessage("请输入顾客人数").setPositiveButton("确定",null).create().show();
                }

                break;
            case R.id.cancelBtn:
                //取消功能
                Log.d(TAG, "onClick: ------------------点击了取消按钮");
                foodList.clear();
                orderedLtv.setVisibility(View.GONE);
                sumTxv.setText(0+"");
                //foodView
                break;
        }
    }

    private void initFoodTypeGv(GridView foodtypeGdv) {
        foodTypes = new String[]{"凉菜", "热菜", "汤菜", "主食", "酒水"};
        final List<RadioButton> rbs=new ArrayList<RadioButton>();
        Log.d(TAG, "onCheckedChanged: -----------------进入initFoodTypeGv"+ foodTypes.length);
        for (int i = 0; i< foodTypes.length; i++){
            Log.d(TAG, "onCheckedChanged: -----------------for循环"+ foodTypes.length);
            RadioButton rb=new RadioButton(daincai.this);
            rb.setText(foodTypes[i]);
            rb.setOnCheckedChangeListener(new RadioButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Log.d(TAG, "onCheckedChanged: -----------------单选按钮变换");
                    if (!b) return;
                    for (RadioButton bb:rbs){
                        if (bb!=compoundButton){
                            bb.setChecked(false);
                        }
                    }
                    String type=compoundButton.getText().toString();
                    initfoodSpn(type);
                    Log.d(TAG, "单选的菜品 -------------"+type);
                }
            });
            rbs.add(rb);
        }
        //rbs.get(1).setChecked(true);
        Log.d(TAG, "initFoodTypeGv: -------------!!!!!!!!!!!!!"+rbs.get(1).getText().toString());
        initfoodSpn("汤菜");
        Log.d(TAG, "initFoodTypeGv: -------------test1");
        foodtypeGdv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return foodTypes.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return rbs.get(i);
            }
        });
        Log.d(TAG, "initFoodTypeGv: -------------test2");
    }

    private void initfoodSpn(String type) {
            Log.d(TAG, "进入initfoodSpn方法的type-------------"+type);
            List<Food> foodList= LitePal.where("kind = ?",type).find(Food.class);
            Log.d(TAG, type+"单选的菜品 -------------"+foodList.get(0).getName());
            ArrayAdapter<Food> arrayAdapter = new ArrayAdapter<Food>(this, R.layout.support_simple_spinner_dropdown_item, foodList);
            foodSpn = foodView.findViewById(R.id.foodSpn);
            foodSpn.setAdapter(arrayAdapter);
    }
    private ArrayList<Map<String,Object>> getData(){

        Log.d(TAG, "getData: 进入了getdata方法--------------------------");
        Food food= (Food) foodSpn.getSelectedItem();
        foodList.add(food);
        int num=1;
        String n=numEdt.getText().toString();
        Log.d(TAG, "onClick: ------------------价格："+n);
        if (n.length()>0){
            num=Integer.parseInt(n);

        }
        nums.add(num);
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Map<String ,Object> line;
        for (int i=0;i<foodList.size();i++){
            line=new HashMap<String ,Object>();
            Log.d(TAG, "getData: ------------------"+foodList.get(i).toString());
            line.put("foodid",foodList.get(i).getId());
            line.put("no",orderedList.size()+1);
            line.put("name",foodList.get(i).getName());
            line.put("description","备注");
            line.put("num",nums.get(i));
            line.put("price",foodList.get(i).getPrice());
            line.put("checked",true);
            data.add(line);
        }
        orderedLtv.setVisibility(View.VISIBLE);
        refreshSum(num*food.getPrice());
        return data;
    }

    public void refreshSum(double v) {
        int sum= Integer.parseInt(sumTxv.getText().toString());
        sum+=v;
        sumTxv.setText(sum+"");
    }
    private void initOrderedLtb(){
        SimpleAdapter sa=new SimpleAdapter(this,orderedList,R.layout.order,orderedLtvKeys,orderedLtvIds){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final int lineNo=position;
                View view=super.getView(position,convertView,parent);
                CheckBox checkBox=view.findViewById(R.id.checkCkb);
                checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        Map<String,Object> line= (Map<String, Object>) getItem(lineNo);
                        int num= (int) line.get("num");
                        int price= (int) line.get("price");
                        if (b){
                            refreshSum(num*price);
                        }else
                            refreshSum(-num*price);
                        line.put("checked",b);
                    }
                });
                return view;
            }

        };
        orderedLtv.setAdapter(sa);
    }
}
