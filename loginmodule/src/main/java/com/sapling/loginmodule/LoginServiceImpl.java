package com.sapling.loginmodule;

import android.content.Context;
import android.content.Intent;

import com.sapling.compilemodule.ILoginService;

/**
 * create by cral
 * create at 2019/11/26
 **/
public class LoginServiceImpl implements ILoginService {
    @Override
    public void skip(Context context) {
        context.startActivity(new Intent(context,LoginActivity.class));
    }
}
