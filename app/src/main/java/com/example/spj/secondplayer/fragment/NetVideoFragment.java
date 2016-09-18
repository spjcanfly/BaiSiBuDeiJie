package com.example.spj.secondplayer.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.spj.secondplayer.R;
import com.example.spj.secondplayer.activity.ShowPhotoActivity;
import com.example.spj.secondplayer.adapter.MyAdapter;
import com.example.spj.secondplayer.bean.CaoNiMa;
import com.example.spj.secondplayer.utils.CacheUtils;
import com.example.spj.secondplayer.utils.Constants;
import com.example.spj.secondplayer.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by spj on 2016/9/14.
 */
public class NetVideoFragment extends BaseFragment {

    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.tv_nomedia)
    TextView tvNomedia;
    @Bind(R.id.refresh)
    MaterialRefreshLayout refresh;
    private List<CaoNiMa.ListBean> list;
    private MyAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_net_audio, null);
        ButterKnife.bind(this, view);

        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getDataFromNet();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                getMoreDataFromNet();
            }
        });


        listview.setOnItemClickListener(new MyOnItemClickListener());
        return view;
    }

    //加载更多
    private void getMoreDataFromNet() {

        OkHttpUtils.get()
                .url(Constants.AUDIO_MORE)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        processedMoreData(response);
                        refresh.finishRefreshLoadMore();
                    }
                });

    }

    private void processedMoreData(String response) {
        list.addAll(parsedJson(response).getList());
        adapter.notifyDataSetChanged();
    }


    //从网络获得数据
    private void getDataFromNet() {


        //使用OKhttp第三方封装库请求网络
        OkHttpUtils.get()
                .url(Constants.AUDIO_URL)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    @Override
    public void initData() {
        super.initData();

        String saveJson = CacheUtils.getString(mContext, Constants.AUDIO_URL);
        if (!TextUtils.isEmpty(saveJson)) {
            processedData(saveJson);
        }
        getDataFromNet();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private class MyStringCallback extends StringCallback {


        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("使用okhttp联网请求失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("使用okhttp联网请求数据成功==" + response);
            //缓存数据
            CacheUtils.putString(mContext, Constants.AUDIO_URL, response);

            processedData(response);
            refresh.finishRefresh();
        }
    }

    //获得解析后的bean对象
    private void processedData(String response) {

        CaoNiMa bean = parsedJson(response);
        list = bean.getList();
        if (list != null && list.size() > 0) {
            //有东西
            tvNomedia.setVisibility(View.GONE);
            adapter = new MyAdapter(mContext, list);
            listview.setAdapter(adapter);
        } else {
            //没有东西
            tvNomedia.setVisibility(View.VISIBLE);
            tvNomedia.setText("啥也没有啊");
        }

        progressbar.setVisibility(View.GONE);

    }

    //解析json数据
    private CaoNiMa parsedJson(String response) {
        return new Gson().fromJson(response, CaoNiMa.class);
    }


    private class MyOnItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            Intent intent = new Intent(mContext, ShowPhotoActivity.class);
            if ("image".equals(list.get(position).getType())) {
                String imgUrl = list.get(position).getImage().getBig().get(0);
                intent.putExtra("imgUrl", imgUrl);
                intent.putExtra("isImage", true);
            }


            if ("gif".equals(list.get(position).getType())) {
                String gifUrl = list.get(position).getGif().getImages().get(0);

                intent.putExtra("gifUrl", gifUrl);
            }

            mContext.startActivity(intent);

        }
    }






}
