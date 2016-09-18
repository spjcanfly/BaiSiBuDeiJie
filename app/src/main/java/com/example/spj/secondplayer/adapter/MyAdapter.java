package com.example.spj.secondplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.secondplayer.R;
import com.example.spj.secondplayer.bean.CaoNiMa;
import com.example.spj.secondplayer.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by spj on 2016/9/14.
 */
public class MyAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<CaoNiMa.ListBean> lists;
    /**
     * 视频
     */
    private static final int TYPE_VIDEO = 0;

    /**
     * 图片
     */
    private static final int TYPE_IMAGE = 1;

    /**
     * 文字
     */
    private static final int TYPE_TEXT = 2;

    /**
     * GIF图片
     */
    private static final int TYPE_GIF = 3;


    /**
     * 软件推广
     */
    private static final int TYPE_AD = 4;
    private final Utils utils;


    public MyAdapter(Context mContext, List<CaoNiMa.ListBean> list) {
        this.mContext = mContext;
        this.lists = list;
        utils = Utils.getInstance();
    }


    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        //初始化item布局
        ViewHolder viewHolder = null;
        int itemViewType = getItemViewType(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            switch (itemViewType) {
                case TYPE_VIDEO://视频
                    convertView = View.inflate(mContext, R.layout.all_video_item, null);
                    //在这里实例化特有的
                    viewHolder.tv_play_nums = (TextView) convertView.findViewById(R.id.tv_play_nums);
                    viewHolder.tv_video_duration = (TextView) convertView.findViewById(R.id.tv_video_duration);
                    viewHolder.iv_commant = (ImageView) convertView.findViewById(R.id.iv_commant);
                    viewHolder.tv_commant_context = (TextView) convertView.findViewById(R.id.tv_commant_context);
                    viewHolder.jcv_videoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
                    break;
                case TYPE_IMAGE://图片
                    convertView = View.inflate(mContext, R.layout.all_image_item, null);
                    viewHolder.iv_image_icon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
                    break;
                case TYPE_TEXT://文字
                    convertView = View.inflate(mContext, R.layout.all_text_item, null);
                    break;
                case TYPE_GIF://gif
                    convertView = View.inflate(mContext, R.layout.all_gif_item, null);
                    viewHolder.iv_image_gif = (GifImageView) convertView.findViewById(R.id.iv_image_gif);
//                    viewHolder.iv_image_gif = (ImageView) convertView.findViewById(R.id.iv_image_gif);
                    break;
                case TYPE_AD://软件广告
                    convertView = View.inflate(mContext, R.layout.all_ad_item, null);
                    viewHolder.btn_install = (Button) convertView.findViewById(R.id.btn_install);
                    viewHolder.iv_image_icon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
                    break;
            }

            initCommonView(convertView, itemViewType, viewHolder);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        bindData(position, itemViewType, viewHolder);

//        viewHolder.btn_install.setOnClickListener(new MyOnclick());

        return convertView;
    }

    //返回总类型数据
    @Override
    public int getViewTypeCount() {
        return 5;
    }

    /**
     * 当前item是什么类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        //根据位置，从列表中得到一个数据对象
        CaoNiMa.ListBean bean = lists.get(position);
        String type = bean.getType();//得到类型
        if ("video".equals(type)) {
            itemViewType = TYPE_VIDEO;
        } else if ("image".equals(type)) {
            itemViewType = TYPE_IMAGE;
        } else if ("text".equals(type)) {
            itemViewType = TYPE_TEXT;
        } else if ("gif".equals(type)) {
            itemViewType = TYPE_GIF;
        } else {
            itemViewType = TYPE_AD;//广播
        }

        return itemViewType;
    }

    /**
     * 初始化公共的控件
     *
     * @param convertView
     * @param itemViewType
     * @param viewHolder
     */
    private void initCommonView(View convertView, int itemViewType, ViewHolder viewHolder) {
        switch (itemViewType) {
            case TYPE_VIDEO://视频
            case TYPE_IMAGE://图片
            case TYPE_TEXT://文字
            case TYPE_GIF://gif
                //加载除开广告部分的公共部分视图
                //user info
                viewHolder.iv_headpic = (ImageView) convertView.findViewById(R.id.iv_headpic);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tv_time_refresh = (TextView) convertView.findViewById(R.id.tv_time_refresh);
                viewHolder.iv_right_more = (ImageView) convertView.findViewById(R.id.iv_right_more);
                //bottom
                viewHolder.iv_video_kind = (ImageView) convertView.findViewById(R.id.iv_video_kind);
                viewHolder.tv_video_kind_text = (TextView) convertView.findViewById(R.id.tv_video_kind_text);
                viewHolder.tv_shenhe_ding_number = (TextView) convertView.findViewById(R.id.tv_shenhe_ding_number);
                viewHolder.tv_shenhe_cai_number = (TextView) convertView.findViewById(R.id.tv_shenhe_cai_number);
                viewHolder.tv_posts_number = (TextView) convertView.findViewById(R.id.tv_posts_number);
                viewHolder.ll_download = (LinearLayout) convertView.findViewById(R.id.ll_download);

                break;
        }


        //中间公共部分 -所有的都有
        viewHolder.tv_context = (TextView) convertView.findViewById(R.id.tv_context);
    }

//    1.绑定特殊数据

    private void bindData(int position, int itemViewType, ViewHolder viewHolder) {
        //根据位置得到数据,绑定数据
        CaoNiMa.ListBean listBean = lists.get(position);


        switch (itemViewType) {
            case TYPE_VIDEO://视频
                bindData(viewHolder, listBean);
                //第一个参数是视频播放地址，第二个参数是listview显示，第三参数是标题
                boolean setUp = viewHolder.jcv_videoplayer.setUp(listBean.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST, listBean.getText());
                if (setUp) {
                    ImageLoader.getInstance().displayImage(listBean.getVideo().getThumbnail().get(0),
                            viewHolder.jcv_videoplayer.thumbImageView);
                }
                viewHolder.tv_play_nums.setText(listBean.getVideo().getPlaycount() + "次播放");
                viewHolder.tv_video_duration.setText(utils.stringForTime(listBean.getVideo().getDuration() * 1000) + "");

                break;
            case TYPE_IMAGE://图片
                bindData(viewHolder, listBean);
                viewHolder.iv_image_icon.setImageResource(R.drawable.bg_item);
                int height = listBean.getImage().getHeight() <= DensityUtil.getScreenHeight() * 0.75 ? listBean.getImage().getHeight() : (int) (DensityUtil.getScreenHeight() * 0.75);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.getScreenWidth(), height);
                viewHolder.iv_image_icon.setLayoutParams(params);
                if (listBean.getImage() != null && listBean.getImage().getBig() != null && listBean.getImage().getBig().size() > 0) {
//                    x.image().bind(viewHolder.iv_image_icon, mediaItem.getImage().getBig().get(0));
                    Glide.with(mContext).load(listBean.getImage().getBig().get(0)).placeholder(R.drawable.bg_item).error(R.drawable.bg_item).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.iv_image_icon);
                }
                break;
            case TYPE_TEXT://文字
                bindData(viewHolder, listBean);
                break;
            case TYPE_GIF://gif
                bindData(viewHolder, listBean);
                System.out.println("mediaItem.getGif().getImages().get(0)" + listBean.getGif().getImages().get(0));
                Glide.with(mContext).load(listBean.getGif().getImages().get(0)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(viewHolder.iv_image_gif);

                break;
            case TYPE_AD://软件广告
                break;
        }


        //设置文本
        viewHolder.tv_context.setText(listBean.getText());

    }
//    2.绑定公共数据

    private void bindData(ViewHolder viewHolder, CaoNiMa.ListBean bean) {
        if (bean.getU() != null && bean.getU().getHeader() != null && bean.getU().getHeader().get(0) != null) {
            ImageOptions imageOptions = new ImageOptions.Builder()
                    .setIgnoreGif(false)//是否忽略gif图。false表示不忽略。不写这句，默认是true
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setFailureDrawableId(R.drawable.ic_tab_audio)
                    .setLoadingDrawableId(R.drawable.ic_tab_audio)
                    .build();
            x.image().bind(viewHolder.iv_headpic, bean.getU().getHeader().get(0), imageOptions);
//            Glide.with(mContext).load(bean.getU().getHeader().get(0)).into(viewHolder.iv_headpic);

        }
        if (bean.getU() != null && bean.getU().getName() != null) {

            if(viewHolder.tv_name != null) {
                viewHolder.tv_name.setText(bean.getU().getName() + "");

            }
        }

        if(viewHolder.tv_time_refresh != null) {
            viewHolder.tv_time_refresh.setText(bean.getPasstime());
        }


        //设置标签
        List<CaoNiMa.ListBean.TagsBean> tags = bean.getTags();
        if (tags != null && tags.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < tags.size(); i++) {
                buffer.append(tags.get(i).getName() + " ");
            }

            if(viewHolder.tv_video_kind_text != null) {
                viewHolder.tv_video_kind_text.setText(buffer.toString());
            }

        }

        //设置点赞，踩,转发
        if(viewHolder.tv_shenhe_ding_number != null) {

            viewHolder.tv_shenhe_ding_number.setText(bean.getUp());
        }
        if(viewHolder.tv_shenhe_cai_number != null) {

            viewHolder.tv_shenhe_cai_number.setText(bean.getDown() + "");
        }
        if(viewHolder.tv_posts_number != null) {
            //马丹，这里少了个“”
            viewHolder.tv_posts_number.setText(bean.getForward()+"");
        }


    }


    static class ViewHolder {
        //user_info
        ImageView iv_headpic;
        TextView tv_name;
        TextView tv_time_refresh;
        ImageView iv_right_more;
        //bottom
        ImageView iv_video_kind;
        TextView tv_video_kind_text;
        TextView tv_shenhe_ding_number;
        TextView tv_shenhe_cai_number;
        TextView tv_posts_number;
        LinearLayout ll_download;

        //中间公共部分 -所有的都有
        TextView tv_context;


        //Video
//        TextView tv_context;
        TextView tv_play_nums;
        TextView tv_video_duration;
        ImageView iv_commant;
        TextView tv_commant_context;
        JCVideoPlayerStandard jcv_videoplayer;

        //Image
        ImageView iv_image_icon;
//        TextView tv_context;

        //Text
//        TextView tv_context;

        //Gif
        GifImageView iv_image_gif;
//        ImageView iv_image_gif; 用imageview控件设置gif
//        TextView tv_context;

        //软件推广
        Button btn_install;
//        TextView iv_image_icon;
        //TextView tv_context;


    }

    //安装软件
//    private class MyOnclick implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
//            OkHttpUtils//
//                    .get()//
//                    .url(url)//
//                    .build()//
//                    .execute(new FileCallBack(Environment.getDownloadCacheDirectory().getAbsolutePath(),"gson-2.2.1.jar") {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//
//                            LogUtil.e("error"+e.getMessage());
//
//                        }
//
//                        @Override
//                        public void onResponse(File file, int id) {
//
//                            LogUtil.e(file.getAbsolutePath());
//
//                        }
//                    });
//        }
//    }
}
