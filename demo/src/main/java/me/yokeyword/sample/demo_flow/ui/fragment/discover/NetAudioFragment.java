package me.yokeyword.sample.demo_flow.ui.fragment.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.util.LogUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.sample.R;
import me.yokeyword.sample.demo_flow.adapter.MyRecycleViewAdapter;
import me.yokeyword.sample.demo_flow.base.BaseFragment;
import me.yokeyword.sample.demo_flow.bean.CaoNiMa;
import me.yokeyword.sample.demo_flow.utils.CacheUtils;
import me.yokeyword.sample.demo_flow.utils.Constants;
import okhttp3.Call;

/**
 * Created by spj on 2016/9/14.
 * 用RecycleView加载
 */
public class NetAudioFragment extends BaseFragment {

    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.refresh)
    MaterialRefreshLayout refresh;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.tv_nomedia)
    TextView tvNomedia;
    private List<CaoNiMa.ListBean> list;
    private MyRecycleViewAdapter adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycle_audio, container, false);

        initView(view);

        initData();

        return view;
    }

    public View initView(View view) {

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

    public void initData() {
        String saveJson = CacheUtils.getString(_mActivity, Constants.AUDIO_URL);
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
            CacheUtils.putString(_mActivity, Constants.AUDIO_URL, response);

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
            adapter = new MyRecycleViewAdapter(_mActivity, list);
            recycleview.setAdapter(adapter);
            //注意recycleview必须要加上这一句
            recycleview.setLayoutManager(new LinearLayoutManager(_mActivity,LinearLayoutManager.VERTICAL,false));
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
}
