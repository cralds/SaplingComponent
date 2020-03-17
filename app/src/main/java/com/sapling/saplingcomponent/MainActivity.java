package com.sapling.saplingcomponent;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sapling.compilemodule.ServiceManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AIDLServiceConnection serviceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.vp);

        tabLayout.addTab(tabLayout.newTab().setText("首页"));
        tabLayout.addTab(tabLayout.newTab().setText("商城"));




        fragmentList.add(new HomeFragment());
        fragmentList.add(ServiceManager.getInstance().getiMallService().getMallFragment());

        FragmentPagerAdapter fpa = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(fpa);
        //setupWithViewPager  设置之后会到只标题不显示
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("首页");
        tabLayout.getTabAt(1).setText("商城");




        findViewById(R.id.btnRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceConnection.request();
            }
        });
        serviceConnection = new AIDLServiceConnection();
        serviceConnection.bindService(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        serviceConnection.unBindService(this);
    }
}
