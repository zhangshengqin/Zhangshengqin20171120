package com.bwie.test.fjd.fenlei02;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.bwie.test.fjd.fenlei01.AddCarModel;

import java.util.Map;

/**
 * Created by asus on 2017/10/17.
 */

public class AddCarPresenter {
    private final Context context;
    private final AddCarView addCarView;
    private final AddCarModel addCarModel;

    public AddCarPresenter(Context context, AddCarView addCarView) {
        this.context = context;
        this.addCarView = addCarView;
        addCarModel = new AddCarModel();
    }

    public void addCar(Map<String, Object> map) {
        addCarModel.addCar(context, map, new OkhttpCall() {
            @Override
            public void onFailure(String e, final String msg) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addCarView.onAddCarFail(msg);
                    }
                });

            }

            @Override
            public void onResponse(final String result) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(result)) {
                            addCarView.onAddCarSuc(result);
                        }
                    }
                });
            }
        });
    }
}
