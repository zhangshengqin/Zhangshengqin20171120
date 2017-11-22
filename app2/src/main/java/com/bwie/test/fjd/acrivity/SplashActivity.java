package com.bwie.test.fjd.acrivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bwie.test.fjd.R;

import java.util.Timer;
import java.util.TimerTask;



/**
 * 闪屏页跳转
 */
public class SplashActivity extends AppCompatActivity {

    private TextView titem;
    private Timer timer;
    private static final int FLAG = 0x123;
    //Handler跳转
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FLAG:
                    int timercount = (int) msg.obj;
                    if (timercount != 0) {
                        titem.setText("跳过(" + timercount + "秒)");
                    } else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        timer.cancel();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //找到控件
        titem = (TextView) findViewById(R.id.mybtnintnet);

            //自定义时间方法
        countdown();

        titem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                timer.cancel();
            }
        });
    }
            //跳转时间
    private void countdown() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            private int count = 3;

            @Override
            public void run() {
                if (count != 0) {
                    count--;
                }
                Message message = handler.obtainMessage();
                message.what = FLAG;
                message.obj = count;
                handler.sendMessage(message);
            }
        }, 0, 1000);
    }

}
