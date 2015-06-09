package com.rickyshin.coolweather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;
import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import citylist.CityListActivity;

/**
 * Created by �ջ� on 2015/6/3.
 */
public class WeatherActivity extends SuperActivity {

    private Context mContext;
    private PullToRefreshView mPullToRefreshView;
    private ScrollView mScrollView;


    private TextView
            tv_city,
            tv_release,
            tv_now_weather,
            tv_today_temp,
            tv_aqi, //空气质量指数
            tv_quality,//空气质量

    tv_next_three,  //小时温度
            tv_next_six,
            tv_next_nine,
            tv_next_twelve,
            tv_next_fifteen,

    tv_temp_three, //小时
            tv_temp_six,
            tv_temp_nine,
            tv_temp_twelve,
            tv_temp_fifteen,


            tv_tomorrow,   //明天
            tv_tomorrow_temp_a,
            tv_tomorrow_temp_b,

            tv_third,       //第三天
            tv_third_temp_a,
            tv_third_temp_b,

            tv_fourth,      //第四天
            tv_fourth_temp_a,
            tv_fourth_temp_b,

            tv_felt_air_temp,
            tv_humidity,//湿度
            tv_wind,    //风向风力
            tv_uv_index,//紫外线指数
            tv_dressing_index, //穿衣指数
            tv_exercise_index; //锻炼指数

    private ImageView
            iv_now_weather, //现在
            iv_next_three,      //小时天气
            iv_next_six,
            iv_next_nine,
            iv_next_twelve,
            iv_next_fifteen,

            iv_tomorrow_weather,
            iv_thirdday_weather,
            iv_fourthday_weather;

    private ImageButton button_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);


        mContext = this;

        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_release = (TextView) findViewById(R.id.tv_release);
        tv_now_weather = (TextView) findViewById(R.id.tv_now_weather);
        tv_today_temp = (TextView) findViewById(R.id.tv_today_temp);
//        tv_aqi = (TextView) findViewById(R.id.tv_aqi);
//        tv_quality = (TextView) findViewById(R.id.tv_quality);

//        tv_next_three = (TextView) findViewById(R.id.tv_next_three);
//        tv_next_six = (TextView) findViewById(R.id.tv_next_six);
//        tv_next_nine = (TextView) findViewById(R.id.tv_next_nine);
//        tv_next_twelve = (TextView) findViewById(R.id.tv_next_twelve);
//        tv_next_fifteen = (TextView) findViewById(R.id.tv_next_fifteen);
//
//        tv_temp_three = (TextView) findViewById(R.id.tv_temp_three);
//        tv_temp_six = (TextView) findViewById(R.id.tv_temp_six);
//        tv_temp_nine = (TextView) findViewById(R.id.tv_temp_nine);
//        tv_temp_twelve = (TextView) findViewById(R.id.tv_temp_twelve);
//        tv_temp_fifteen = (TextView) findViewById(R.id.tv_temp_fifteen);


        tv_tomorrow = (TextView) findViewById(R.id.tv_tomorrow);
        tv_tomorrow_temp_a = (TextView) findViewById(R.id.tv_tomorrow_temp_a);
        tv_tomorrow_temp_b = (TextView) findViewById(R.id.tv_tomorrow_temp_b);

        tv_third = (TextView) findViewById(R.id.tv_third);
        tv_third_temp_a = (TextView) findViewById(R.id.tv_third_temp_a);
        tv_third_temp_b = (TextView) findViewById(R.id.tv_third_temp_b);

        tv_fourth = (TextView) findViewById(R.id.tv_fourth);
        tv_fourth_temp_a = (TextView) findViewById(R.id.tv_fourth_temp_a);
        tv_fourth_temp_b = (TextView) findViewById(R.id.tv_fourth_temp_b);

        tv_felt_air_temp = (TextView) findViewById(R.id.tv_felt_air_temp);
        tv_humidity = (TextView) findViewById(R.id.tv_humidity);
        tv_wind = (TextView) findViewById(R.id.tv_wind);
        tv_uv_index = (TextView) findViewById(R.id.tv_uv_index);
        tv_dressing_index = (TextView) findViewById(R.id.tv_dressing_index);
        tv_exercise_index = (TextView) findViewById(R.id.tv_exercise_index);
//        tv_ = (TextView) findViewById(R.id.tv_);

        iv_now_weather = (ImageView) findViewById(R.id.iv_now_weather);

//        iv_next_three = (ImageView) findViewById(R.id.iv_next_three);
//        iv_next_six = (ImageView) findViewById(R.id.iv_next_six);
//        iv_next_nine = (ImageView) findViewById(R.id.iv_next_nine);
//        iv_next_twelve = (ImageView) findViewById(R.id.iv_next_twelve);
//        iv_next_fifteen = (ImageView) findViewById(R.id.iv_next_fifteen);


        iv_tomorrow_weather = (ImageView) findViewById(R.id.iv_tomorrow_weather);
        iv_thirdday_weather = (ImageView) findViewById(R.id.iv_third_weather);
        iv_fourthday_weather = (ImageView) findViewById(R.id.iv_fourth_weather);


        button_city = (ImageButton) findViewById(R.id.button_city);
        button_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeatherActivity.this, CityListActivity.class);
                startActivity(intent);
            }
        });

        Intent intent2 = getIntent();
        String city = intent2.getStringExtra("city");
        if (city == null) getWeatherData("北京");
        else getWeatherData(city);

        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 499);
            }
        });
    }

    private void getWeatherData(String city) {
        /** 请不要添加key参数. */
        Parameters params = new Parameters();
        params.add("cityname", city);
        params.add("dtype", "json");

        /** 请求的方法 参数:
         第一个参数 当前请求的context;
         第二个参数 接口id;
         第三个参数 接口请求的url;
         第四个参数 接口请求的方式;
         第五个参数 接口请求的参数,键值对com.thinkland.sdk.android.Parameters类型;
         第六个参数请求的回调方法,com.thinkland.sdk.android.DataCallBack; */

        JuheData.executeWithAPI(mContext, 39, "http://v.juhe.cn/weather/index", JuheData.GET, params, new DataCallBack() {
            /** 请求成功时调用的方法 statusCode为http状态码,responseString为请求返回数据. */
            @Override
            public void onSuccess(int statusCode, String responseString) { /* TODO Auto-generated method stub tv_tomoorrow.append(responseString + "\n");*/
                parserJson(responseString);
                Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_SHORT).show();
            }

            /** 请求完成时调用的方法,无论成功或者失败都会调用. */
            @Override
            public void onFinish() {

            }

            /** 请求失败时调用的方法,statusCode为http状态码,
             * throwable为捕获到的异常
             * statusCode:30002 没有检测到当前网络.
             * 30003 没有进行初始化. 0 未明异常,
             * 具体查看Throwable信息. 其他异常请参照http状态码. */
            @Override
            public void onFailure(int statusCode, String responseString, Throwable throwable) {
                finish();
                Toast.makeText(getApplicationContext(), "服务器连接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parserJson(String StrRe) {
        try {

            /**当天预报的数据解析*/
            JSONObject jsonObj1 = new JSONObject(StrRe).getJSONObject("result").getJSONObject("today");
            String now_weather = jsonObj1.getString("weather");
            String city = jsonObj1.getString("city");
            String uv_index = jsonObj1.getString("uv_index");
            String exercise_index = jsonObj1.getString("exercise_index");
            String dressing_index = jsonObj1.getString("dressing_index");
            tv_now_weather.setText(now_weather);
            tv_city.setText(city);
            tv_uv_index.setText(uv_index);
            tv_dressing_index.setText(dressing_index);
            tv_exercise_index.setText(exercise_index);

            /**当天天气图片解析*/
            JSONObject weatherId = jsonObj1.getJSONObject("weather_id");
            String fa = weatherId.getString("fa");
            iv_now_weather.setImageResource(getResources().getIdentifier("d" + fa, "drawable", "com.rickyshin.coolweather"));

            /**当天湿度等数据*/
            JSONObject jsonObj2 = new JSONObject(StrRe).getJSONObject("result").getJSONObject("sk");
            String today_temp = jsonObj2.getString("temp");
            String release = jsonObj2.getString("time");
            String humidity = jsonObj2.getString("humidity");
            String felt_air_temp = jsonObj2.getString("temp");
            String wind_direction = jsonObj2.getString("wind_direction");
            String wind_strength = jsonObj2.getString("wind_strength");
            tv_today_temp.setText(today_temp + " °");
            tv_release.setText(release + "  更新");
            tv_humidity.setText(humidity);
            tv_felt_air_temp.setText(felt_air_temp + "℃");
            tv_wind.setText(wind_direction + "   " + wind_strength);

            /**第二天预报的数据解析*/
            Date d = new Date();
            JSONObject jsonTomorrow =
                    new JSONObject(StrRe).getJSONObject("result").getJSONObject("future").getJSONObject("day_" + d.date1());

            String[] tempArr1 = jsonTomorrow.getString("temperature").split("~");
            String temp1_a = tempArr1[0].substring(0, tempArr1[0].indexOf("℃"));
            String temp1_b = tempArr1[1].substring(0, tempArr1[1].indexOf("℃"));
            String weather1 = jsonTomorrow.getString("weather");
            String week1 = jsonTomorrow.getString("week");
            String week1_1 = week1.substring(2, 3);
            tv_tomorrow.setText("周" + week1_1);
            tv_tomorrow_temp_a.setText(weather1);
            tv_tomorrow_temp_b.setText(temp1_a + "°/" + temp1_b + "°");

            /**第二天天气图片解析*/
            JSONObject tom_weather_id = jsonTomorrow.getJSONObject("weather_id");
            String tom_id = tom_weather_id.getString("fa");
            iv_tomorrow_weather.setImageResource(getResources().getIdentifier("d" + tom_id, "drawable", "com.rickyshin.coolweather"));

            /**第三天预报的数据解析*/
            JSONObject jsonThirdDay =
                    new JSONObject(StrRe).getJSONObject("result").getJSONObject("future").getJSONObject("day_" + d.date2());

            String[] tempArr2 = jsonTomorrow.getString("temperature").split("~");
            String temp2_a = tempArr2[0].substring(0, tempArr1[0].indexOf("℃"));
            String temp2_b = tempArr2[1].substring(0, tempArr1[1].indexOf("℃"));
            String weather2 = jsonThirdDay.getString("weather");
            String week2 = jsonThirdDay.getString("week");
            String week2_1 = week2.substring(2, 3);
            tv_third.setText("周" + week2_1);
            tv_third_temp_a.setText(weather2);
            tv_third_temp_b.setText(temp2_a + "°/" + temp2_b + "°");
            /**第三天天气图片解析*/
            JSONObject third_weather_id = jsonThirdDay.getJSONObject("weather_id");
            String Third_id = third_weather_id.getString("fa");
            iv_thirdday_weather.setImageResource(getResources().getIdentifier("d" + Third_id, "drawable", "com.rickyshin.coolweather"));

            /**第四天预报的数据解析*/
            JSONObject jsonFourthDay =
                    new JSONObject(StrRe).getJSONObject("result").getJSONObject("future").getJSONObject("day_" + d.date3());

            String[] tempArr3 = jsonTomorrow.getString("temperature").split("~");
            String temp3_a = tempArr3[0].substring(0, tempArr1[0].indexOf("℃"));
            String temp3_b = tempArr3[1].substring(0, tempArr1[1].indexOf("℃"));
            String weather3 = jsonFourthDay.getString("weather");
            String week3 = jsonFourthDay.getString("week");
            String week3_1 = week3.substring(2, 3);
            tv_fourth.setText("周" + week3_1);
            tv_fourth_temp_a.setText(weather3);
            tv_fourth_temp_b.setText(temp3_a + "°/" + temp3_b + "°");
            /**第四天天气图片解析*/
            JSONObject fourth_weather_id = jsonFourthDay.getJSONObject("weather_id");
            String fourth_id = fourth_weather_id.getString("fa");
            iv_fourthday_weather.setImageResource(getResources().getIdentifier("d" + fourth_id, "drawable", "com.rickyshin.coolweather"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            Intent intent = new Intent();
            intent.setAction(SuperActivity.SYSTEM_EXIT);
            sendBroadcast(intent);
        }
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        /**
         * 关闭当前页面正在进行中的请求.
         */
        JuheData.cancelRequests(mContext);
    }

}
