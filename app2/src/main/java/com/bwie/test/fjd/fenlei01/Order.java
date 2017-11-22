package com.bwie.test.fjd.fenlei01;

import java.util.Comparator;

/**
 * Created by asus on 2017/10/15.
 */

public class Order implements Comparator<ShopChildKindBean.DataBean> {
    private boolean flag;
    public Order(boolean flag) {
        this.flag=flag;
    }


    @Override
    public int compare(ShopChildKindBean.DataBean c1, ShopChildKindBean.DataBean c2) {
        if(flag){
            return  (int)( Double.parseDouble(c1.getPrice())-Double.parseDouble(c2.getPrice()));
        }
        else {
            return (int)( Double.parseDouble(c2.getPrice())-Double.parseDouble(c1.getPrice()));
        }
    }
}
