package com.bwie.test.fjd.fenlei01;

import android.content.Context;

import com.bwie.test.fjd.api.ApiUrl;
import com.bwie.test.fjd.fenlei02.OkhttpCall;
import com.bwie.test.fjd.fenlei02.OkhttpUtils;

import java.util.Map;

/**
 * Created by asus on 2017/10/17.
 */

public class AddCarModel {
    public void addCar(Context context, Map<String, Object> map, OkhttpCall okhttpCall) {
        OkhttpUtils.getInstance(context).call(OkhttpMethod.POST, ApiUrl.ADDCARURL, map, okhttpCall);
    }
}
