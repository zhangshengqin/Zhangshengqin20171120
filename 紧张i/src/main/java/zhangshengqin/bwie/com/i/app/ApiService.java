package zhangshengqin.bwie.com.i.app;

import retrofit2.http.GET;
import rx.Observable;
import zhangshengqin.bwie.com.i.bean.Bean;

/**
 * Created by 额头发 on 2017/11/23.
 */

public interface ApiService {
    @GET("iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=vedio")
    Observable<Bean> getData();
}
