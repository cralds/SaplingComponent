package com.sapling.saplingcomponent;

import android.app.Application;

import com.sapling.compilemodule.BaseApplication;
import com.sapling.compilemodule.ServiceManager;

/**
 * create by cral
 * create at 2019/11/26
 **/
public class MyApplication extends Application {
    public static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        for (String app : ServiceManager.applications){
            try {
                Class<?> application = Class.forName(app);
                Object object = application.newInstance();
                if (object instanceof BaseApplication) {
                    ((BaseApplication)object).init();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
