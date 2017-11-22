package com.bwie.test.fjd.dingdanguanli;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.bwie.test.fjd.fenlei01.GetOrderModel;
import com.bwie.test.fjd.fenlei01.OkhttpCall;

import java.util.Map;


/**
 * Created by asus on 2017/10/17.
 */

public class GetOrderPresenter {
    private final GetOrderView getOrderView;
    private Context context;
    private final GetOrderModel getOrderModel;

    public GetOrderPresenter(Context context, GetOrderView getOrderView) {
        this.context = context;
        this.getOrderView = getOrderView;
        getOrderModel = new GetOrderModel();
    }

    public void createOrder(Map<String, Object> map) {
        getOrderModel.getOrder(context, map, new OkhttpCall() {
            @Override
            public void onFailure(String e, final String msg) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getOrderView.onGetOrderFail(msg);
                    }
                });
            }

            @Override
            public void onResponse(final String result) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(result)) {
                            getOrderView.onGetOrderSuc(result);
                        }
                    }
                });
            }
        });

    }

    public void updateOrder(Map<String, Object> map) {
        getOrderModel.updateOrder(context, map, new OkhttpCall() {
            @Override
            public void onFailure(String e, final String msg) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getOrderView.onUpdateOrderFail(msg);
                    }
                });
            }

            @Override
            public void onResponse(final String result) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(result)) {
                            getOrderView.onUpdateOrderSuc(result);
                        }
                    }
                });
            }
        });
    }
}
