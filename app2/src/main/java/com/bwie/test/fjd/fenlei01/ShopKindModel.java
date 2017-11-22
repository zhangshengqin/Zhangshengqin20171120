package com.bwie.test.fjd.fenlei01;

import android.content.Context;

import java.util.Map;

/**
 * Created by asus on 2017/10/15.
 */

public class ShopKindModel {

    public void gainShopKind(Context context, String String, Map<String, Object> map, OkhttpCall okhttpCall) {

        OkhttpUtils.getInstance(context).call(OkhttpMethod.POST, String, map, okhttpCall);

    }
}
