package com.example.spj.secondplayer.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by spj on 2016/9/14.
 */
public class VideoFragment extends BaseFragment{
    private TextView tv;

    @Override
    public View initView() {
        tv = new TextView(mContext);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(18);
        tv.setTextColor(Color.BLACK);
        return tv;
    }

    @Override
    public void initData() {
        super.initData();
        tv.setText("本地音频");
    }
}
