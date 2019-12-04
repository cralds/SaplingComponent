package com.sapling.compilemodule;

/**
 * create by cral
 * create at 2019/11/26
 **/
public class ServiceManager {
    private static volatile  ServiceManager serviceManager;

    private IMallService iMallService;
    private ILoginService iLoginService;

    public static String[] applications = new String[]{
            "com.sapling.loginmodule.LoginApplication",
            "com.sapling.mallmodule.MallApplication",
            "com.sapling.loginmodule.LoginApplication"
    };

    public static ServiceManager getInstance(){
        if (serviceManager == null){
            synchronized (ServiceManager.class){
                if (serviceManager == null){
                    serviceManager = new ServiceManager();
                }
            }
        }
        return serviceManager;
    }

    public IMallService getiMallService() {
        return iMallService;
    }

    public ILoginService getiLoginService() {
        return iLoginService;
    }

    public void setiLoginService(ILoginService iLoginService) {
        this.iLoginService = iLoginService;
    }

    public void setiMallService(IMallService iMallService) {
        this.iMallService = iMallService;
    }
}
