package com.bwie.test.fjd.gouwuche;

/**
 * Created by asus on 2017/10/15.
 */

public interface OkhttpCall {
    void onFailure(String e, String msg);//e:异常数据，msg：请求失败提示

    void onResponse(String result);//请求成功json串
}
