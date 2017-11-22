package com.bwie.test.fjd.dingdanguanli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.bwie.test.fjd.R;

/**
 * Created by Administrator on 2017/11/15.
 */

public class ZhiFu extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhifu);
        Button wwwww = (Button) findViewById(R.id.wwwww);
        wwwww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhiFu.this, PayDemoActivity.class);
                startActivity(intent);
            }
        });

    }
}
