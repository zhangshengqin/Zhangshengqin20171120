package com.bwie.test.fjd.gouwuche;

import android.app.Activity;
import android.content.Context;

import com.bwie.test.fjd.fenlei01.SearchCarModel;

import java.util.Map;


/**
 * Created by asus on 2017/10/17.
 */

public class SearchPresenter {

    private Context context;
    private SearchCarView searchCarView;
    private final SearchCarModel searchCarModel;

    public SearchPresenter(Context context, SearchCarView searchCarView) {
        this.context = context;
        this.searchCarView = searchCarView;
        searchCarModel = new SearchCarModel();
    }
    public void searchCar(Map<String,Object> map){
        searchCarModel.searchCar(context, map, new OkhttpCall() {
            @Override
            public void onFailure(String e, final String msg) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
searchCarView.onSearchCarFail("请求失败");
                    }
                });
            }

            @Override
            public void onResponse(final String result) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ceshi======search======="+result);
searchCarView.onSearchCarSuc(result);
                    }
                });
            }
        });

    }
    public void updateCar(Map<String,Object> map){
        searchCarModel.updateCar(context, map, new OkhttpCall() {
            @Override
            public void onFailure(String e, final String msg) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchCarView.onUpdateCarFail(msg);
                    }
                });
            }

            @Override
            public void onResponse(final String result) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ceshi======update======="+result);
                        searchCarView.onUpdateCarSuc(result);
                    }
                });
            }
        });

    }
}
