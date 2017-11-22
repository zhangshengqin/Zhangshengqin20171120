package com.bwie.test.fjd.fenlei;

import android.text.TextUtils;

/**
 * Created by asus on 2017/10/8.
 */

public class ChildPresenter implements ChildKindModel.ChildListener {

    private final ChildKindView childview;
    private final ChildKindModel childKindModel;

    public ChildPresenter(ChildKindView childKindView) {
        this.childview = childKindView;
        childKindModel = new ChildKindModel();
        childKindModel.setChild(this);
    }

    public void gainChildKind(String cid) {
        if (!TextUtils.isEmpty(cid)) {
            childKindModel.gainChildKind(cid);
        }
    }

    @Override
    public void gainSuc(String data) {
        if (!TextUtils.isEmpty(data)) {
            childview.gainSuc(data);
        }
    }

    @Override
    public void gainFail() {
        childview.gainFail();
    }
}
