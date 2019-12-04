package com.sapling.mallmodule;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.sapling.compilemodule.IMallService;

/**
 * create by cral
 * create at 2019/11/26
 **/
public class MallServiceImpl implements IMallService {
    @Override
    public void skip(Context context) {
        context.startActivity(new Intent(context,MallActivity.class));
    }

    @Override
    public Fragment getMallFragment() {
        return new MallFragment();
    }
}
