package com.sapling.mallmodule;

import com.sapling.compilemodule.BaseApplication;
import com.sapling.compilemodule.ServiceManager;

/**
 * create by cral
 * create at 2019/11/26
 **/
public class MallApplication implements BaseApplication {
    @Override
    public void init() {
        ServiceManager.getInstance().setiMallService(new MallServiceImpl());
    }
}
