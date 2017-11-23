package zhangshengqin.bwie.com.i.utils;

import android.app.Application;

import zhangshengqin.bwie.com.i.gen.DaoMaster;
import zhangshengqin.bwie.com.i.gen.DaoSession;
import zhangshengqin.bwie.com.i.gen.UserDao;

/**
 * Created by 额头发 on 2017/11/23.
 */

public class App extends Application{
    public static UserDao userDao;



    @Override

    public void onCreate() {

        super.onCreate();

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lenvess.db", null);

        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());

        DaoSession daoSession = daoMaster.newSession();

        userDao = daoSession.getUserDao();

    }
}
