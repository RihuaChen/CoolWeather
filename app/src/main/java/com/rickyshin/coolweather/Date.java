package com.rickyshin.coolweather;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ÈÕ»ª on 2015/6/8.
 */
public class Date {

    public String date1() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");

        c.add(Calendar.DAY_OF_MONTH, 1);
        String day1 = sim.format(c.getTime());
        return day1;
    }

    public String date2() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");

        c.add(Calendar.DAY_OF_MONTH, 2);
        String day2 = sim.format(c.getTime());
        return day2;
    }

    public String date3() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");

        c.add(Calendar.DAY_OF_MONTH, 3);
        String day3 = sim.format(c.getTime());
        return day3;
    }

    public String date4() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");

        c.add(Calendar.DAY_OF_MONTH, 4);
        String day4 = sim.format(c.getTime());
        return day4;
    }

    public String date5() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");

        c.add(Calendar.DAY_OF_MONTH, 5);
        String day5 = sim.format(c.getTime());
        return day5;
    }

    public String date6() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");

        c.add(Calendar.DAY_OF_MONTH, 6);
        String day6 = sim.format(c.getTime());
        return day6;
    }

    public String date7() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");

        c.add(Calendar.DAY_OF_MONTH, 7);
        String day7 = sim.format(c.getTime());
        return day7;
    }

}
