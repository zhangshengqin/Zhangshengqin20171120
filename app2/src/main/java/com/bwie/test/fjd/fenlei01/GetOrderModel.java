package com.bwie.test.fjd.fenlei01;

import android.content.Context;

import com.bwie.test.fjd.api.ApiUrl;

import java.util.Map;

/**
 * Created by asus on 2017/10/17.
 */

public class GetOrderModel {
    public void getOrder(Context context, Map<String,Object> map, OkhttpCall okhttpCall){
        OkhttpUtils.getInstance(context).call(OkhttpMethod.POST, ApiUrl.GETORDER,map,okhttpCall);
    }
    public void updateOrder(Context context, Map<String,Object> map, OkhttpCall okhttpCall){
        OkhttpUtils.getInstance(context).call(OkhttpMethod.POST, ApiUrl.UPDATEORDER,map,okhttpCall);
    }

}
