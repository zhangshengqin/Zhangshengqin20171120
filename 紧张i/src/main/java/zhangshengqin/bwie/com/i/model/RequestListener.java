package zhangshengqin.bwie.com.i.model;

import java.util.List;

import zhangshengqin.bwie.com.i.bean.Bean;

/**
 * Created by 额头发 on 2017/11/23.
 */

public interface RequestListener {
    void OnSuccess(List<Bean.DataBean> list);
    void OnError(String r);
}
