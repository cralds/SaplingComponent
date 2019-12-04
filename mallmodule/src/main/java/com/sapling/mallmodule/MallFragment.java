package com.sapling.mallmodule;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sapling.compilemodule.ServiceManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class MallFragment extends Fragment {

    private View mianView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mianView = inflater.inflate(R.layout.fragment_mall, container, false);
        return mianView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mianView.findViewById(R.id.mall_tvLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceManager.getInstance().getiLoginService().skip(getActivity());
            }
        });
    }
}
