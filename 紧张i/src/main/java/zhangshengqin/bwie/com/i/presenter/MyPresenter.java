package zhangshengqin.bwie.com.i.presenter;

import java.util.List;

import zhangshengqin.bwie.com.i.bean.Bean;
import zhangshengqin.bwie.com.i.model.MyModel;
import zhangshengqin.bwie.com.i.model.MyModelInfo;
import zhangshengqin.bwie.com.i.model.RequestListener;
import zhangshengqin.bwie.com.i.view.MyViewInfo;

/**
 * Created by 额头发 on 2017/11/23.
 */

public class MyPresenter {
    MyViewInfo myViewInfo;
    MyModelInfo myModelInfo;

    public MyPresenter(MyViewInfo myViewInfo) {
        this.myViewInfo = myViewInfo;
        myModelInfo = new MyModel();
    }

    public void Data(String url){
        myModelInfo.getData(url, new RequestListener() {
            @Override
            public void OnSuccess(List<Bean.DataBean> list) {
                myViewInfo.showSuccess(list);
            }

            @Override
            public void OnError(String r) {
                myViewInfo.failError(r);
            }
        });
    }
}
