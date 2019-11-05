package com.example.liber;

import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MyThread1 extends Thread {
    private String time;
    private String result1;
    private String result2;
    public MyThread1(String time)
    {
        this.time = time;
    }
    public void run()
    {
        while (true) {
            try {
                Connection conn = Jsoup.connect("http://210.44.64.139/touchscreen/seatOrderAction.php?action=payCardLogin").timeout(8000);
                conn.header("Host", "210.44.64.139");
                conn.header("Referer", "http://210.44.64.139/touchscreen/index.php");
                conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
                Map<String, String> map = new HashMap<String, String>();
                map.put("cardno", "3236699395");
                Connection.Response response = conn.ignoreContentType(true).method(Connection.Method.POST).data(map).execute();
                Map<String, String> cookies = response.cookies();
                Document document = Jsoup.connect("http://210.44.64.139/touchscreen/index.php").cookies(cookies).get();
                Elements name = document.select("a[href=#]");//欢迎使用【16027240023】
                result1 = name.text();
                System.out.println(name.text() + "!!!---------------------------------------------");
                //约座部分
                Map<String, String> seat = new HashMap<String, String>();
                seat.put("seat_id", "2451");
                seat.put("order_date", time);
                /*http://210.44.64.139/touchscreen/seatOrderAction.php?action=addOrderSeat*/
                Connection conn2 = Jsoup.connect("http://210.44.64.139/touchscreen/seatOrderAction.php?action=addOrderSeat").timeout(8000);
                conn2.header("Host", "210.44.64.139");
                conn2.header("Referer", "http://210.44.64.139/touchscreen/index.php");
                conn2.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");

                Connection.Response re = conn2.ignoreContentType(true).method(Connection.Method.POST).cookies(cookies).data(seat).execute();
                Map<String, String> cookies2 = re.cookies();
                Document dc = Jsoup.connect("http://210.44.64.139/touchscreen/index.php").cookies(cookies).get();
                Elements result = dc.select("a");
                result2 = result.last().text();
                //Document yuezuo=Jsoup.connect("http://210.44.64.139/touchscreen/seatOrderAction.php?action=addOrderSeat").cookies(cookies).data(seat).post();
                //System.out.println(yuezuo.outerHtml());
                System.out.println(dc.outerHtml());
                System.out.println("----------------------------------------------" + result.last().text());//退出
                System.out.println(document.outerHtml());
                System.out.println(result1+"------------------判断-----+++++++++"+result2);
                if (result1.equals("欢迎使用【16027240023】")&&result2.equals("退出")){
                    break;
                }

            } catch (IOException e) {
                Log.d(TAG, "onCreate: ------------抛出异常" + e.toString());
            }

        }

    }
}
