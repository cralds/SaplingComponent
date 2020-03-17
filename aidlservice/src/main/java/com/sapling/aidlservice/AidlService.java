package com.sapling.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AidlService extends Service {
    public AidlService() {
    }
    private IClientInterface clientInterface;
    IServiceInterface.Stub iServiceInterface = new IServiceInterface.Stub() {

        @Override
        public void registerListener(IClientInterface iClientInterface) throws RemoteException {
            clientInterface = iClientInterface;
        }

        @Override
        public void unRegisterListener(IClientInterface iClientInterface) throws RemoteException {
            clientInterface = null;
        }

        @Override
        public void request(String name) throws RemoteException {
            Log.d("sssssssssss","aidl server receive request the name is =="+ name);
            if ("cral".equals(name)){
                clientInterface.requestSuccess("success");
            }else {
                clientInterface.requestFail("error username");
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return iServiceInterface;
    }
}
