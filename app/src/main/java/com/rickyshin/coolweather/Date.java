package com.rickyshin.coolweather;

import android.widget.TextView;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ÈÕ»ª on 2015/6/8.
 */
public class Date {

    public String getDate(int dayFromToday) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");

        c.add(Calendar.DAY_OF_MONTH, dayFromToday);
        String day = sim.format(c.getTime());
        return day;
    }

}
