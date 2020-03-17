package com.sapling.saplingcomponent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.sapling.aidlservice.IClientInterface;
import com.sapling.aidlservice.IServiceInterface;

import java.util.List;

/**
 * create by cral
 * create at 2020/3/17
 **/
public class AIDLServiceConnection implements ServiceConnection {

    private IServiceInterface iServiceInterface;
    private boolean isConnection = false;

    private IClientInterface iClientInterface = new IClientInterface.Stub() {
        @Override
        public void requestSuccess(String result) throws RemoteException {
            Log.d("sssssssssss","aidl request success =="+ result);
        }

        @Override
        public void requestFail(String msg) throws RemoteException {
            Log.d("sssssssssss","aidl request fail =="+ msg);
        }


    };
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iServiceInterface = IServiceInterface.Stub.asInterface(service);
        try {
            iServiceInterface.registerListener(iClientInterface);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        isConnection = true;

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isConnection = false;
        try {
            iServiceInterface.unRegisterListener(iClientInterface);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        iServiceInterface = null;

    }

    public void request(){
        if (!isConnection){
            return;
        }
        try {
            iServiceInterface.request("cral");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnection() {
        return isConnection;
    }

    public void bindService(Context context){
        Intent intent = new Intent();
        intent.setAction("com.sapling.aidl.INTENT");
        context.bindService(createExplicitFromImplicitIntent(context,intent),this,Context.BIND_AUTO_CREATE);
    }

    public void unBindService(Context context){
        context.unbindService(this);
    }

    private Intent createExplicitFromImplicitIntent(Context context,Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
