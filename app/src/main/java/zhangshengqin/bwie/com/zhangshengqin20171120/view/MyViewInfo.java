package zhangshengqin.bwie.com.zhangshengqin20171120.view;

import java.util.List;

import zhangshengqin.bwie.com.zhangshengqin20171120.bean.Bean;

/**
 * Created by 额头发 on 2017/11/20.
 */

public interface MyViewInfo {
    void showSuccess(List<Bean.RetBean.ListBean> list);
    void failError(String msg);

}
