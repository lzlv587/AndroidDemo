package com.example.liber;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.app.AlarmManager.RTC_WAKEUP;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Calendar mCalendar;
    private static final String TAG = "";
    private AlarmManager alarmManager;
    private TextView info;
    private TextView show;
    private TextView show2;
    private TextView show3;
    private TextView show4;
    private EditText sitenum1;
    private EditText sitenum2;
    private int room;
    private int site1;
    private int site2;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 100:
                    show.setText("正在预约");
                    break;
                case 101:
                    show2.setText("正在预约");
                    break;
                case 102:
                    show3.setText("正在预约");
                    break;
                case 103:
                    show4.setText("正在预约");
                    break;
                case 1:
                    show.setText("291预约成功");
                    break;
                case 11:
                    show2.setText("292预约成功");
                    break;
                case 12:
                    show3.setText("276预约成功");
                    break;
                case 13:
                    show4.setText("280预约成功");
                    break;
                case 2:
                    String result=show.getText().toString();
                    if (result.equals("成功")){
                        break;
                    }else {
                        show.setText("失败");
                        break;
                    }
                case 3:
                    show.setText("登录成功");
                    break;
                case 31:
                    show2.setText("登陆成功");
                    break;
                case 32:
                    show3.setText("登录成功");
                    break;
                case 33:
                    show4.setText("登陆成功");
                    break;
                case 4:
                    show.setText("座位1已被别人预约");
                    break;
                case 42:
                    show2.setText("座位2已被别人预约");
                    break;
                case 43:
                    show3.setText("座位3已被别人预约");
                    break;
                case 44:
                    show4.setText("座位4已被别人预约");
                    break;
                case 5:
                    show.setText("闭馆，请预约明天的座位！");
                    show2.setText("闭馆，请预约明天的座位！");
                    show3.setText("闭馆，请预约明天的座位！");
                    show4.setText("闭馆，请预约明天的座位！");
                    break;
            }
        }
    };
    private Button btntest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        sitenum1=findViewById(R.id.sitenumber);
        sitenum2=findViewById(R.id.sitenumber2);*/

        show=findViewById(R.id.tv_show);
        show2=findViewById(R.id.tv_show2);
        show3=findViewById(R.id.tv_show3);
        show4=findViewById(R.id.tv_show4);
        btntest = findViewById(R.id.btntestt);
        btntest.setOnClickListener(this);


/*        String[] ctype = new String[]{"一楼101A", "一楼101B", "一楼107(1-160)", "一楼107(160-420)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner = super.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/

    }
   /* public int  getrealsite(int site){
        int result = 0;
        if (1<=site&&site<=4){
            switch (site){
                case 1:
                    result=713;break;
                case 2:
                    result=714;break;
                case 3:
                    result=701;break;
                case 4:
                    result=702;break;
            }
        }else if (5<=site&&site<=32){
            if(site%2!=0){
                //奇数
                result=665+(site-5)*12;
            }else {
                //偶数
                result=666+(site-5)*12;

            }
        }
        Log.d(TAG, "getrealsite: ---------------"+result);
        return result;
    };
*/
    @Override
    public void onClick(View v) {
//        site1= Integer.parseInt(sitenum1.getText().toString());
//        site2= Integer.parseInt(sitenum2.getText().toString());
        switch (v.getId()) {
            /*case R.id.btnnaozhong:
                mCalendar= Calendar.getInstance();
                mCalendar.setTimeInMillis(System.currentTimeMillis());
                int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = mCalendar.get(Calendar.MINUTE);
                new TimePickerDialog(MainActivity.this, minute, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //是设置日历的时间，主要是让日历的年月日和当前同步
                        mCalendar.setTimeInMillis(System.currentTimeMillis());
                        //设置小时分钟，秒和毫秒都设置为0
                        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        mCalendar.set(Calendar.MINUTE, minute);
                        mCalendar.set(Calendar.SECOND, 0);
                        mCalendar.set(Calendar.MILLISECOND, 0);
                        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                        intent.putExtra("hourOfDay",hourOfDay);
                        intent.putExtra("minute",minute);
                        startActivity(intent);
                        PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                        //得到AlarmManager实例
                        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                        //根据当前时间预设一个警报
                        am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);
                        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(10*1000),
                                30000, pi);
                        String msg = "设置闹钟时间为"+hourOfDay+":"+minute;
                        info.setText(msg);
                    }
                }, hour, minute, true).show();
                break;*/
            case R.id.btntestt:
                Log.d(TAG, "onClick: 点击了按钮++++");
                try {
/*                    getrealsite(site1);
                    getrealsite(site2);*/
                   /* MyThread myThread=new MyThread();
                    new Thread(myThread).start();*/
/*                    testThread testThread=new testThread(handler);
                    testThread.start();
                    testThread testThread4=new testThread(handler);
                    testThread4.start();
                    testThread1 testThread1=new testThread1(handler);
                    testThread1.start();*/
                    //Toast.makeText(this,"预约成功",Toast.LENGTH_SHORT).show();
                    testThreadyqc1 t1=new testThreadyqc1(handler);
                    t1.start();
                    testThreadyqc1 t11=new testThreadyqc1(handler);
                    t11.start();
                    testThreadyqc2 t2=new testThreadyqc2(handler);
                    t2.start();
                    testThreadyqc2 t21=new testThreadyqc2(handler);
                    t21.start();
                    testThreadyqc3 t3=new testThreadyqc3(handler);
                    t3.start();
                    testThreadyqc3 t31=new testThreadyqc3(handler);
                    t31.start();
                    testThreadyqc4 t4=new testThreadyqc4(handler);
                    t4.start();
                    testThreadyqc4 t41=new testThreadyqc4(handler);
                    t41.start();


                }catch (Exception e){
                    Toast.makeText(this,"预约失败啊",Toast.LENGTH_SHORT).show();
                }


                /*Calendar calendar= Calendar.getInstance();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                Log.d(TAG, "onClick: -------------"+dateFormat.format(calendar.getTime()));*/

                break;
            /*case R.id.quxiao:
*//*                Toast.makeText(this,"点击了取消按钮",Toast.LENGTH_SHORT).show();

                Handler handler=new Handler();
                handler.postDelayed(new testThread(), 2000);*//*

                //得到日历实例，主要是为了下面的获取时间
                *//*mCalendar = Calendar.getInstance();
                mCalendar.setTimeInMillis(System.currentTimeMillis());
                //获取当前毫秒值
                long systemTime = System.currentTimeMillis();
                //是设置日历的时间，主要是让日历的年月日和当前同步
                mCalendar.setTimeInMillis(System.currentTimeMillis());
                // 这里时区需要设置一下，不然可能个别手机会有8个小时的时间差
                // mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                //设置在几点提醒 设置的为13点
                mCalendar.set(Calendar.HOUR_OF_DAY, 20);
                //设置在几分提醒 设置的为25分
                mCalendar.set(Calendar.MINUTE, 56);
                //下面这两个看字面意思也知道
                mCalendar.set(Calendar.SECOND, 0);
                mCalendar.set(Calendar.MILLISECOND, 0);
                //上面设置的就是13点25分的时间点
                //获取上面设置的13点25分的毫秒值
                long selectTime = mCalendar.getTimeInMillis();

                // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
                if(systemTime > selectTime) {
                    mCalendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                //AlarmReceiver.class为广播接受者
                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                //得到AlarmManager实例
                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);

*//**//*                  重复提醒
                  第一个参数是警报类型；下面有介绍
                第二个参数网上说法不一，很多都是说的是延迟多少毫秒执行这个闹钟，但是我用的刷了MIUI的三星手机的实际效果是与单次提醒的参数一样，即设置的13点25分的时间点毫秒值
                 第三个参数是重复周期，也就是下次提醒的间隔 毫秒值 我这里是一天后提醒*//**//*

                am.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), (1000 * 60 * 60 * 24), pi);*//*





                // 进行闹铃注册
              *//*  Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                // 过2s 执行这个闹铃
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.SECOND, 2);
                AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);*//*




                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0,
                        intent, 0);
                AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
                //取消警报
                am.cancel(pi);
                Toast.makeText(this, "关闭了提醒", Toast.LENGTH_SHORT).show();
                break;*/
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                Toast.makeText(this,"选择了101A",Toast.LENGTH_SHORT).show();
                room=1;
                btntest.setOnClickListener(this);
                break;
            case 1:
                Toast.makeText(this,"选择了101B",Toast.LENGTH_SHORT).show();
                room=2;
                btntest.setOnClickListener(this);
                break;
            case 2:
                Toast.makeText(this,"一楼107(1-160)",Toast.LENGTH_SHORT).show();
                room=3;
                btntest.setOnClickListener(this);
                break;
            case 3:
                Toast.makeText(this,"一楼107(160-420)",Toast.LENGTH_SHORT).show();
                room=4;
                btntest.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
