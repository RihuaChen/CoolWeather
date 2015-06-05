package com.rickyshin.coolweather;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

/**
 * Created by �ջ� on 2015/6/3.
 */
public class WeatherActivity extends AppCompatActivity{

    private Context mContext;
    private PullToRefreshView mPullToRefreshView;
    private ScrollView mScrollView;



    private TextView tv_city,
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

            tv_today_temp_a, //今天
            tv_todaty_temp_b,

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
            tv_dressing_index; //穿衣指数

    private ImageView iv_now_weather, //现在
            iv_next_three,      //小时天气
            iv_next_six,
            iv_next_nine,
            iv_next_twelve,
            iv_next_fifteen,

            iv_today_weather,    //每日天气
            iv_tomorrow_weather,
            iv_thirdday_weather,
            iv_fourthday_weather;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

//        tv_tomoorrow = (TextView) findViewById(R.id.tv_tomorrow);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_release = (TextView) findViewById(R.id.tv_release);
        tv_now_weather = (TextView) findViewById(R.id.tv_now_weather);
        tv_today_temp = (TextView) findViewById(R.id.tv_today_temp);
        tv_aqi = (TextView) findViewById(R.id.tv_aqi);
        tv_quality = (TextView) findViewById(R.id.tv_quality);

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

        tv_today_temp_a = (TextView) findViewById(R.id.tv_today_temp_a);
        tv_todaty_temp_b = (TextView) findViewById(R.id.tv_today_temp_b);

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
//        tv_ = (TextView) findViewById(R.id.tv_);

        iv_now_weather = (ImageView) findViewById(R.id.iv_now_weather);

//        iv_next_three = (ImageView) findViewById(R.id.iv_next_three);
//        iv_next_six = (ImageView) findViewById(R.id.iv_next_six);
//        iv_next_nine = (ImageView) findViewById(R.id.iv_next_nine);
//        iv_next_twelve = (ImageView) findViewById(R.id.iv_next_twelve);
//        iv_next_fifteen = (ImageView) findViewById(R.id.iv_next_fifteen);

        iv_today_weather = (ImageView) findViewById(R.id.iv_today_weather);
        iv_tomorrow_weather = (ImageView) findViewById(R.id.iv_tomorrow_weather);
        iv_thirdday_weather = (ImageView) findViewById(R.id.iv_third_weather);
        iv_fourthday_weather = (ImageView) findViewById(R.id.iv_fourth_weather);






        /**
         * 请不要添加key参数.
         */
        Parameters params = new Parameters();
        params.add("cityname", "莆田"  );
        params.add("dtype", "json");
        /**
         * 请求的方法 参数: 第一个参数 当前请求的context;
         * 				  第二个参数 接口id;
         * 				  第三个参数 接口请求的url;
         * 				  第四个参数 接口请求的方式;
         * 				  第五个参数 接口请求的参数,键值对com.thinkland.sdk.android.Parameters类型;
         * 				  第六个参数请求的回调方法,com.thinkland.sdk.android.DataCallBack;
         *
         */
        JuheData.executeWithAPI(mContext, 39, "http://v.juhe.cn/weather/index",
                JuheData.GET, params, new DataCallBack() {
                    /**
                     * 请求成功时调用的方法 statusCode为http状态码,responseString为请求返回数据.
                     */
                    @Override
                    public void onSuccess(int statusCode, String responseString) {
                        // TODO Auto-generated method stub
//                        tv_tomoorrow.append(responseString + "\n");
                        parserJson(responseString);
                    }

                    /**
                     * 请求完成时调用的方法,无论成功或者失败都会调用.
                     */
                    @Override
                    public void onFinish() {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), "finish",
                                Toast.LENGTH_SHORT).show();
                    }

                    /**
                     * 请求失败时调用的方法,statusCode为http状态码,throwable为捕获到的异常
                     * statusCode:30002 没有检测到当前网络.
                     * 			  30003 没有进行初始化.
                     * 			  0 未明异常,具体查看Throwable信息.
                     * 			  其他异常请参照http状态码.
                     */
                    @Override
                    public void onFailure(int statusCode, String responseString, Throwable throwable) {
                        // TODO Auto-generated method stub
//                        tv_tomoorrow.append(throwable.getMessage() + "\n");


                    }
                });

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


    private void parserJson(String StrRe) {
        try {

            JSONObject jsonObj1 = new JSONObject(StrRe).getJSONObject("result").getJSONObject("today");
            String city = jsonObj1.getString("city");
            String now_weather = jsonObj1.getString("weather");
            String today_temp = jsonObj1.getString("temperature");
            String uv_index = jsonObj1.getString("uv_index");

            String dressing_index = jsonObj1.getString("dressing_index");

            tv_city.setText(city);
            tv_today_temp.setText(today_temp);
            tv_now_weather.setText(now_weather);
            tv_uv_index.setText(uv_index);

            tv_dressing_index.setText(dressing_index);


            JSONObject jsonObj2 = new JSONObject(StrRe).getJSONObject("result").getJSONObject("sk");
            String release = jsonObj2.getString("time");
            String humidity = jsonObj2.getString("humidity");
            String felt_air_temp = jsonObj2.getString("temp");
            String wind_direction = jsonObj2.getString("wind_direction");
            String wind_strength = jsonObj2.getString("wind_strength");

            tv_release.setText(release + "  更新");
            tv_humidity.setText(humidity);
            tv_felt_air_temp.setText(felt_air_temp + "℃");
            tv_wind.setText(wind_direction + "   " + wind_strength);




        } catch (JSONException ex) {
            ex.printStackTrace();
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