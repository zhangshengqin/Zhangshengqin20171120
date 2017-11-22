package com.bwie.test.fjd.gouwuche;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.bwie.test.fjd.fenlei01.CreateOrderModel;

import java.util.Map;


/**
 * Created by asus on 2017/10/17.
 */

public class CreateOrderPresenter {

    private final CreateOrderView createOrderView;

    private Context context;

    private final CreateOrderModel createOrderModel;

    public CreateOrderPresenter(Context context, CreateOrderView createOrderView) {

        this.context = context;

        this.createOrderView = createOrderView;

        createOrderModel = new CreateOrderModel();
    }

    public void createOrder(Map<String, Object> map) {

        createOrderModel.createOrder(context, map, new com.bwie.test.fjd.fenlei01.OkhttpCall() {
            @Override
            public void onFailure(String e, final String msg) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        createOrderView.onCreateOrderFail(msg);
                    }
                });
            }

            @Override
            public void onResponse(final String result) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(result)) {
                            createOrderView.onCreateOrderSuc(result);
                        }
                    }
                });
            }
        });

    }
}
