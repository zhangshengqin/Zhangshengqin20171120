package zhangshengqin.bwie.com.i.view;

import java.util.List;

import zhangshengqin.bwie.com.i.bean.Bean;

/**
 * Created by 额头发 on 2017/11/23.
 */

public interface MyViewInfo {
    void showSuccess(List<Bean.DataBean> list);
    void failError(String msg);
}
