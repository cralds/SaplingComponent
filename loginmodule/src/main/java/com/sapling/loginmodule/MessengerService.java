package com.sapling.loginmodule;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * 使用messenger和handler实现跨进程通信
 */
public class MessengerService extends Service {
    private Messenger messenger;
    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        messenger = new Messenger(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1){
                    String name = msg.getData().getString("name");
                    Log.d("ssssssssssss","login sevice receive request  = " + name);
                    Message message = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putString("result","i got your name is " + name);
                    message.setData(bundle);
                    message.what = 2;
                    try {
                        msg.replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
