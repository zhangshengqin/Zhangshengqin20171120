package zhangshengqin.bwie.com.zhangshengqin20171120.model;

import java.util.List;

import zhangshengqin.bwie.com.zhangshengqin20171120.bean.Bean;

/**
 * Created by 额头发 on 2017/11/20.
 */

public interface RequestListener {
    void onSuccess(List<Bean.RetBean.ListBean> list);
    void onError(String r);
}
