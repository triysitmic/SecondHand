package com.lby.secondhand.application;

import android.app.Application;

import com.lby.secondhand.dao.api.DAOModule;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initialDatabase();
    }

    private void initialDatabase() {
        DAOModule.getInstance().initializeDaoSession(this);
    }
}
