package com.bwie.test.fjd.fenlei;

import com.bwie.test.fjd.api.ApiUrl;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by asus on 2017/9/29.
 */

public class ChildKindModel {


    private ChildListener child;

    public void gainChildKind(String cid) {

        OkHttpClient okHttpClient = new OkHttpClient();
        //post请求用这个方法
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("cid", cid);
        FormBody build = builder.build();
        Request request = new Request.Builder().url(ApiUrl.ChildKindUrl).post(build).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                child.gainFail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

                if (string != null) {
                    Gson gson = new Gson();
                    ChildKindBean childKindBean = gson.fromJson(string, ChildKindBean.class);
                    if ("0".equals(childKindBean.getCode())) {
                        child.gainSuc(string);
                    }

                }

            }
        });

    }

    public void setChild(ChildListener child) {
        this.child = child;
    }

    public interface ChildListener {
        void gainSuc(String data);

        void gainFail();

    }
}
