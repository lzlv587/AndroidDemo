package com.example.liber;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive: ----------------------test");
        String hourOfDay=intent.getStringExtra("hourOfDay");
        String minute=intent.getStringExtra("minute");
        Log.d(TAG, "onReceive: -------------------接收到的开始的时间"+hourOfDay+"-----"+minute);
//        Toast.makeText(context,"设置了闹钟",Toast.LENGTH_SHORT).show();
        //MyThread1 myThread=new MyThread1("");
        //new Thread(myThread).start();
    }
}
