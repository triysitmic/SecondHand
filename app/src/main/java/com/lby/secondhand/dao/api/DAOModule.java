package com.lby.secondhand.dao.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.lby.secondhand.dao.ShopApiDaoCode;
import com.lby.secondhand.dao.bean.DaoMaster;
import com.lby.secondhand.dao.bean.DaoSession;
import com.lby.secondhand.dao.bean.GoodsType;
import com.lby.secondhand.dao.bean.User;
import com.lby.secondhand.method.ICallback;
import com.lby.secondhand.method.ShopApiProvider;
import com.lby.secondhand.method.ShopApiProviderImpl;

import java.util.Map;


public class DAOModule {

    private static final String TAG = "DAOModule";
    private static final String DB_NAME = "shop.db";
    @SuppressLint("StaticFieldLeak")
    private static DAOModule instance = new DAOModule();
    private static DaoSession daoSession = null;
    private ShopApiProvider shopApiProvider = new ShopApiProviderImpl();

    private DAOModule() {
    }

    public static DAOModule getInstance() {
        return instance;
    }

    public void initializeDaoSession(Context context) {
        setUpDatabaseIfNeed(context);
    }

    private DaoSession getDaoSession() {
        return daoSession;
    }

    private void setUpDatabaseIfNeed(Context context) {
        if (daoSession != null) {
            return;
        }
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        daoSession = master.newSession();
        Log.d(TAG, "dao initialize :" + daoSession.toString());
    }

    public void insertNewUser(User user, ICallback callback) {
        shopApiProvider.insertNewUser(daoSession, user, callback);
    }

    public void queryUserByName(String name, ICallback callback) {
        shopApiProvider.queryUserByName(daoSession, name, callback);
    }

    public void insertNewGoodsType(GoodsType type, ICallback callback) {
        shopApiProvider.insertNewGoodsType(daoSession, type, callback);
    }

    public void getAllGoodsType(ICallback callback) {
        shopApiProvider.queryAllGoodsType(daoSession, callback);
    }

    public void getAllGoods(ICallback callback) {
        shopApiProvider.queryAllGoods(daoSession, callback);
    }

    public void getGoodsByUserId(Long id, ICallback callback) {
        shopApiProvider.queryGoodsByPersonalId(daoSession, id, callback);
    }
}
