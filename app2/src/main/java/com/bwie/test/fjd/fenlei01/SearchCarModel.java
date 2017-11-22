package com.bwie.test.fjd.fenlei01;

import android.content.Context;

import com.bwie.test.fjd.api.ApiUrl;
import com.bwie.test.fjd.gouwuche.OkhttpCall;
import com.bwie.test.fjd.gouwuche.OkhttpUtils;

import java.util.Map;

/**
 * Created by asus on 2017/10/17.
 */

public class SearchCarModel {
    public void searchCar(Context context, Map<String,Object> map, com.bwie.test.fjd.gouwuche.OkhttpCall okhttpCall){
        OkhttpUtils.getInstance(context).call(OkhttpMethod.POST, ApiUrl.SEARCHCARURL,map,okhttpCall);
    }
    public void updateCar(Context context, Map<String,Object> map, OkhttpCall okhttpCall){
        OkhttpUtils.getInstance(context).call(OkhttpMethod.POST, ApiUrl.UPDATECARURL,map,okhttpCall);
    }
}
