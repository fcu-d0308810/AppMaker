package com.fcu.jslab.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //綁定登入按鈕ID
        Button login_button = (Button)findViewById(R.id.Login_Button);
        //設定login_button跳轉頁面
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(login_page.this , Mainpage.class);
                startActivity(intent);

            }
        });
        //註冊按鈕綁ID
        Button registered_button = (Button)findViewById(R.id.Registered_Button);
        //設定註冊的跳轉頁面
        registered_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(login_page.this , register_page.class);
                startActivity(intent);
            }
        });
    }
}
