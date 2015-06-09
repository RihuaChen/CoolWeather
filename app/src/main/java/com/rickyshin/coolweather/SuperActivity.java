package com.rickyshin.coolweather;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


/**
 * Created by 日华 on 2015/6/6.
 */
public class SuperActivity extends Activity {
    /**
     * 所有activity都继承此类，同时也就等于注册了广播，
     * 当需要完全退出系统的时候就可以发送广播，
     * action为com.example.exitsystem.system_exit（自定义），
     * 这样就可以随时退出所有的activity了
     * @author LinZhiquan
     *
     */
    public static final String SYSTEM_EXIT = "com.rickyshin.coolweather.system_exit";
    /** 广播action */
    private MyReceiver receiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //发送广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(SYSTEM_EXIT);
        receiver = new MyReceiver();
        this.registerReceiver(receiver, filter);


    }

    @Override
    protected void onDestroy() {
        //注销广播
        this.unregisterReceiver(receiver);
        super.onDestroy();
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }
}
