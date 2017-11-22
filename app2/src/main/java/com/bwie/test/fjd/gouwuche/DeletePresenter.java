package com.bwie.test.fjd.gouwuche;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.bwie.test.fjd.fenlei01.DeleteCarModel;

import java.util.Map;



/**
 * Created by asus on 2017/10/17.
 */

public class DeletePresenter {
    private final DeleteCarView deleteCarView;
    private Context context;
    private final DeleteCarModel deleteCarModel;

    public DeletePresenter(Context context, DeleteCarView deleteCarView) {
        this.context = context;
        this.deleteCarView=deleteCarView;
        deleteCarModel = new DeleteCarModel();
    }
    public void deleteCar(Map<String,Object> map){
        deleteCarModel.deleteCar(context, map, new com.bwie.test.fjd.fenlei01.OkhttpCall() {
            @Override
            public void onFailure(String e, final String msg) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        deleteCarView.onDeleteCarFail(msg);
                    }
                });
            }

            @Override
            public void onResponse(final String result) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
            if(!TextUtils.isEmpty(result))
            {
                      deleteCarView.onDeleteCarSuc(result);
            }
                    }
                });
            }
        });

    }
}
