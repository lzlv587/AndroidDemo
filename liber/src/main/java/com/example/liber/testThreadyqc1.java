package com.example.liber;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class testThreadyqc1 extends Thread {
    private static final String TAG = "dd";
    private Handler handler;
    private String result1;
    private String result2;
    Map<String, String> cookies;
    private String[] bodys;
    public testThreadyqc1(Handler handler){this.handler=handler;};
    @Override
    public void run() {




        if (result1==null)
        result1="begin";
        if (result2==null)
        result2="begin";
        while (!result1.equals("欢迎使用【16057140046】")) {
            try {
               /* //登录部分
               //获取当前时间*/

                Connection conn = Jsoup.connect("http://210.44.64.139/touchscreen/seatOrderAction.php?action=payCardLogin").timeout(30000);
                conn.header("Host", "210.44.64.139");
                conn.header("Referer", "http://210.44.64.139/touchscreen/index.php");
                conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
                Map<String, String> map = new HashMap<String, String>();
                map.put("cardno", "0278852827");
                Connection.Response response = conn.ignoreContentType(true).method(Connection.Method.POST).data(map).execute();

                cookies = response.cookies();
                Document document = Jsoup.connect("http://210.44.64.139/touchscreen/index.php").cookies(cookies).get();
                Elements name = document.select("a[href=#]");//欢迎使用【16027240023】
                result1 = name.text();
                System.out.println(name.text() + "!!!---------------------------------------------");
                Message m = new Message();
                m.what = 3;
                handler.sendMessage(m);
            } catch (IOException e) {
                Log.d(TAG, "onCreate: ------------登录部分抛出异常" + e.toString());
                run();
            }
        }
        while (!result2.equals("预约失败，该座位已经被预约！")){
                //约座部分
            Message mm = new Message();
            mm.what = 100;
            handler.sendMessage(mm);
            //获取当前时间
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int day2 = day+1;
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            String date = year + "-" + month + "-" + day;
            Log.d(TAG, "run: ---------testdate------" + date);
                Map<String, String> seat = new HashMap<String, String>();
                seat.put("seat_id", "2084");//四楼200    2451   107.290   2101  /101 1 713/二楼1    1301
                seat.put("order_date",date);
                /*http://210.44.64.139/touchscreen/seatOrderAction.php?action=addOrderSeat*/
                Connection conn2 = Jsoup.connect("http://210.44.64.139/touchscreen/seatOrderAction.php?action=addOrderSeat").timeout(30000);
                conn2.header("Host", "210.44.64.139");
                conn2.header("Referer", "http://210.44.64.139/touchscreen/index.php");
                conn2.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");

                Connection.Response re = null;
            try {
                re = conn2.ignoreContentType(true).method(Connection.Method.POST).cookies(cookies).data(seat).execute();


                    String body = re.body();
                    Log.d(TAG, "run: !!!!!!!!!!!!!!!!!!!!!!!!" + body);
                    bodys = body.split("\"");
                    Log.d(TAG, "run: !!!!!!!!!!!!!!!!!!!!!!!!" + bodys[5]);


            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "run: -----------------预约出错");
                run();
            }

            //Map<String, String> cookies2 = re.cookies();
            Document dc = null;
            try {
                dc = Jsoup.connect("http://210.44.64.139/touchscreen/index.php").cookies(cookies).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Elements result = dc.select("a");
                result2 = bodys[5];
                //Document yuezuo=Jsoup.connect("http://210.44.64.139/touchscreen/seatOrderAction.php?action=addOrderSeat").cookies(cookies).data(seat).post();
                //System.out.println(yuezuo.outerHtml());
                //System.out.println(dc.outerHtml());
                //System.out.println("----------------------------------------------" + result.last().text());//退出
                //System.out.println(dc.outerHtml());
                System.out.println(result1+"------------------判断-----+++++++++"+result2);
                if (result1.equals("欢迎使用【16057140046】")&&result2.equals("预约成功！")){
                    Message m = new Message();
                    m.what = 1;
                    handler.sendMessage(m);
                    break;
                }else if(result1.equals("欢迎使用【16057140046】")&&result2.equals("预约失败，该座位已经被预约！")){
                    Message m = new Message();
                    m.what = 4;
                    handler.sendMessage(m);

                }else if (result1.equals("欢迎使用【16057140046】")&&result2.equals("闭馆，请预约明天的座位！")){
                    Message m=new Message();
                    m.what=5;
                    handler.sendMessage(m);
                }
                else {
                    Message m = new Message();
                    m.what = 2;
                    handler.sendMessage(m);

                }
            }

        }


    }

