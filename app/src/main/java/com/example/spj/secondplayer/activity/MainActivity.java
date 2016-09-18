package com.example.spj.secondplayer.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.spj.secondplayer.R;
import com.example.spj.secondplayer.fragment.AudioFragment;
import com.example.spj.secondplayer.fragment.BaseFragment;
import com.example.spj.secondplayer.fragment.NetAudioFragment;
import com.example.spj.secondplayer.fragment.NetVideoFragment;
import com.example.spj.secondplayer.fragment.VideoFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg_main;
    private ArrayList<BaseFragment> baseFragments;
    private int position;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initFragment();

        setListener();
    }

    private void setListener() {
        //监听fragment的切换
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //默认选择第一个页面
        rg_main.check(R.id.rb_local_video);
    }

    private void initFragment() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new VideoFragment());
        baseFragments.add(new AudioFragment());
        baseFragments.add(new NetVideoFragment());
        baseFragments.add(new NetAudioFragment());
    }

    private void initView() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
    }

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

            position = 0;

            switch (checkedId) {
                case R.id.rb_local_audio:

                    position = 0;
                    break;
                case R.id.rb_local_video:

                    position = 1;
                    break;
                case R.id.rb_net_audio:

                    position = 2;
                    break;
                case R.id.rb_net_video:

                    position = 3;
                    break;
            }
            //根据位置从集合中取出对应的Fragment
            Fragment toFragment = getFragment(position);

            //把对应的Fragment绑定到Activity中
            switchFragment(fragment, toFragment);
        }
    }

    //隐藏刚刚显示的，显示将要显示的
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {

        if(fragment != toFragment) {
            fragment = toFragment;
            if(toFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if(toFragment.isAdded()) {
                    //添加了
                 transaction.show(toFragment).commit();
                }else {
                    //没有添加
                    transaction.add(R.id.fl_content,toFragment).commit();
                }
                //隐藏fromFragment
                if(fromFragment != null) {
                    transaction.hide(fromFragment);
                }
            }
        }
    }

    private Fragment getFragment(int position) {

        return baseFragments == null ? null : baseFragments.get(position);
    }


}
