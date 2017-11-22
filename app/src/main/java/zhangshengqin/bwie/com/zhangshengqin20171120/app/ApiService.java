package zhangshengqin.bwie.com.zhangshengqin20171120.app;

import retrofit2.http.GET;
import rx.Observable;
import zhangshengqin.bwie.com.zhangshengqin20171120.bean.Bean;

/**
 * Created by 额头发 on 2017/11/20.
 */

public interface ApiService {
    @GET("columns/getVideoList.do?catalogId=402834815584e463015584e539330016&pnum=5")
    Observable<Bean> GetData();
}
