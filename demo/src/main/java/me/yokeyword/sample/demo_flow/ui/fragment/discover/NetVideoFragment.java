package me.yokeyword.sample.demo_flow.ui.fragment.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.sample.R;
import me.yokeyword.sample.ShowPhotoActivity;
import me.yokeyword.sample.demo_flow.adapter.MyAdapter;
import me.yokeyword.sample.demo_flow.base.BaseFragment;
import me.yokeyword.sample.demo_flow.bean.CaoNiMa;
import me.yokeyword.sample.demo_flow.utils.CacheUtils;
import me.yokeyword.sample.demo_flow.utils.Constants;
import me.yokeyword.sample.demo_flow.utils.LogUtil;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = View.inflate(_mActivity, R.layout.fragment_net_audio, null);

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
            adapter = new MyAdapter(_mActivity, list);
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


    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            Intent intent = new Intent(_mActivity, ShowPhotoActivity.class);
            if ("image".equals(list.get(position).getType())) {
                String imgUrl = list.get(position).getImage().getBig().get(0);
                intent.putExtra("imgUrl", imgUrl);
                intent.putExtra("isImage", true);
            }


            if ("gif".equals(list.get(position).getType())) {
                String gifUrl = list.get(position).getGif().getImages().get(0);

                intent.putExtra("gifUrl", gifUrl);
            }

            _mActivity.startActivity(intent);

        }
    }






}
