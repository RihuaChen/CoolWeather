package com.rickyshin.coolweather;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.gc.materialdesign.views.ButtonRectangle;

import citylist.CityListActivity;

/**
 * Created by �ջ� on 2015/6/10.
 */
//ʵ�ֵ�һ��ѡ����н���
public class LoginActivity extends SuperActivity {

    private ButtonRectangle buttonSelect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        buttonSelect = (ButtonRectangle) findViewById(R.id.button_select);
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CityListActivity.class);
                startActivity(intent);
            }
        });
    }
}
