package com.rickyshin.coolweather.bean;

import java.util.List;

/**
 * Created by ÈÕ»ª on 2015/6/5.
 */
public class ResultBean {

    private List<TodayBean> mTodayBeans;
    private List<FutureWeatherBean> mFutureWeatherBeans;

    public List<TodayBean> getTodayBeans() {
        return mTodayBeans;
    }

    public void setTodayBeans(List<TodayBean> todayBeans) {
        mTodayBeans = todayBeans;
    }

    public List<FutureWeatherBean> getFutureWeatherBeans() {
        return mFutureWeatherBeans;
    }

    public void setFutureWeatherBeans(List<FutureWeatherBean> futureWeatherBeans) {
        mFutureWeatherBeans = futureWeatherBeans;
    }
}
