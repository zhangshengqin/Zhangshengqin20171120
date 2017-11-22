package zhangshengqin.bwie.com.zhangshengqin20171120.presenter;

import java.util.List;

import zhangshengqin.bwie.com.zhangshengqin20171120.bean.Bean;
import zhangshengqin.bwie.com.zhangshengqin20171120.model.MyModel;
import zhangshengqin.bwie.com.zhangshengqin20171120.model.MyModelInfo;
import zhangshengqin.bwie.com.zhangshengqin20171120.model.RequestListener;
import zhangshengqin.bwie.com.zhangshengqin20171120.view.MyViewInfo;

/**
 * Created by 额头发 on 2017/11/20.
 */

public class MyPresenter {
    MyViewInfo myViewInfo;
    MyModelInfo myModelInfo;

    public MyPresenter(MyViewInfo myViewInfo) {
        this.myViewInfo = myViewInfo;
        myModelInfo = new MyModel();
    }
    public void GetData(String url){
        myModelInfo.getData(url, new RequestListener() {
            @Override
            public void onSuccess(List<Bean.RetBean.ListBean> list) {
                myViewInfo.showSuccess(list);
            }

            @Override
            public void onError(String r) {
                myViewInfo.failError(r);
            }
        });
    }
}
