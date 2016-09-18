package com.example.spj.secondplayer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by spj on 2016/9/14.
 */
public abstract class BaseFragment extends Fragment{

    public Context mContext;

    //当BaseFragment被创建的时候被系统调用
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    //创建视图的时候调用
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();

    }

    public abstract View initView();

    //当系统创建Activity的时候回调该方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //子类需要数据时，重写该方法
    public void initData() {

    }
}
