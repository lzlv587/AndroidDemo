package com.example.liber;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MyThread implements Runnable {

    private String result1;
    private String result2;



    @Override
    public void run() {

        while (true) {
            try {
//                //获取请求连接
//           /* Map<String,String> myMap= new HashMap<String, String>();
//            myMap.put("cardno","3236699395");
//            Connection conn = Jsoup.connect("http://210.44.64.139/touchscreen/seatOrderAction.php?action=payCardLogin").data(myMap);
//            String cookie="PHPSESSID=f1873389c945db3a2bfb51a3f0c13b37";
//            conn.header("Cookie",cookie);
//            String UserAgent="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
//            conn.header("UserAgent",UserAgent);
//
//            Connection.Response login = conn.ignoreContentType(true).followRedirects(true).method(Connection.Method.POST).data(myMap).execute();
//            Log.d(TAG, "run: ---------------------"+login.body());
//            //获取请求结果
//            Document doc = conn.get();
//            //Document doc=Jsoup.connect("http://210.44.64.139/touchscreen/index.php").get();
//            Elements label=doc.select("label");
//            Elements name=doc.select("a");
//            Element name2=doc.getElementById("navbar");
//            Elements element = doc.select("div[id=navbar]").select("a");
//            Log.d(TAG, "run: ----------------name"+element.text()+"!!");
//            Elements dia=doc.select("div.modal-dialog").select("label");
//            Log.d(TAG, "run: -----------------"+dia.text());*/
//            Log.i(TAG, "run: --------------"+label.size()+label.get(1).text());
//            Elements elements = doc.select("div.form-group").first().select("label");//区域房间日期
                //Log.i(TAG, "onCreate: ---"+elements.text());.
               /* //登录部分
               //获取当前时间*/
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int day2=day+1;
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                String date=year+"-"+month+"-"+day2;
                Log.d(TAG, "run: ---------testdate------"+date);
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
                seat.put("seat_id", "2084");//四楼200    2451   107.290   2084  /101 1 713
                seat.put("order_date",date);
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
    public static String httpGet(String url,String cookie) throws IOException{
//获取请求连接
        Connection con = Jsoup.connect(url);
        //请求头设置，特别是cookie设置
        con.header("Accept", "text/html, application/xhtml+xml, */*");
        con.header("Content-Type", "application/x-www-form-urlencoded");
        con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))");
        con.header("Cookie", cookie);
        //解析请求结果
        Document doc=con.get();
        return doc.toString();
    }
}
