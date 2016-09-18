package me.yokeyword.sample.demo_flow.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.sample.R;
import me.yokeyword.sample.ShowPhotoActivity;
import me.yokeyword.sample.demo_flow.bean.CaoNiMa;
import me.yokeyword.sample.demo_flow.utils.Utils;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by spj on 2016/9/18.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

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

//    public MyRecycleViewAdapter(Context mContext, List<CaoNiMa.ListBean> list) {
//        this.mContext = mContext;
//        this.lists = list;
//        utils = Utils.getInstance();
//    }

    public MyRecycleViewAdapter(SupportActivity mActivity, List<CaoNiMa.ListBean> list) {
        this.mContext = mActivity;
        this.lists = list;
        utils = Utils.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = null;

        switch (viewType) {
            case TYPE_VIDEO://视频
                convertView = View.inflate(mContext, R.layout.all_video_item, null);
                break;
            case TYPE_IMAGE://图片
                convertView = View.inflate(mContext, R.layout.all_image_item, null);
                break;
            case TYPE_TEXT://文字
                convertView = View.inflate(mContext, R.layout.all_text_item, null);
                break;
            case TYPE_GIF://gif
                convertView = View.inflate(mContext, R.layout.all_gif_item, null);
                break;
            case TYPE_AD://软件广告
                convertView = View.inflate(mContext, R.layout.all_ad_item, null);
                break;
        }


        return new ViewHolder(convertView,viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        int itemViewType = getItemViewType(position);
        bindData(position, itemViewType, holder);

        if(holder.iv_image_icon != null) {
            holder.iv_image_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ShowPhotoActivity.class);
                    String imgUrl = lists.get(position).getImage().getBig().get(0);
                    intent.putExtra("imgUrl", imgUrl);
                    intent.putExtra("isImage", true);
                    mContext.startActivity(intent);
                }
            });
        }

        if(holder.iv_image_gif != null) {
            holder.iv_image_gif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ShowPhotoActivity.class);
                    String gifUrl = lists.get(position).getGif().getImages().get(0);

                    intent.putExtra("gifUrl", gifUrl);
                    mContext.startActivity(intent);
                }
            });
        }




    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
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

        }
        if (bean.getU() != null && bean.getU().getName() != null) {

            if (viewHolder.tv_name != null) {
                viewHolder.tv_name.setText(bean.getU().getName() + "");

            }
        }

        if (viewHolder.tv_time_refresh != null) {
            viewHolder.tv_time_refresh.setText(bean.getPasstime());
        }


        //设置标签
        List<CaoNiMa.ListBean.TagsBean> tags = bean.getTags();
        if (tags != null && tags.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < tags.size(); i++) {
                buffer.append(tags.get(i).getName() + " ");
            }

            if (viewHolder.tv_video_kind_text != null) {
                viewHolder.tv_video_kind_text.setText(buffer.toString());
            }

        }

        //设置点赞，踩,转发
        if (viewHolder.tv_shenhe_ding_number != null) {

            viewHolder.tv_shenhe_ding_number.setText(bean.getUp());
        }
        if (viewHolder.tv_shenhe_cai_number != null) {

            viewHolder.tv_shenhe_cai_number.setText(bean.getDown() + "");
        }
        if (viewHolder.tv_posts_number != null) {
            //马丹，这里少了个“”
            viewHolder.tv_posts_number.setText(bean.getForward() + "");
        }


    }

    class ViewHolder extends RecyclerView.ViewHolder {

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
        TextView tv_play_nums;
        TextView tv_video_duration;
        ImageView iv_commant;
        TextView tv_commant_context;
        JCVideoPlayerStandard jcv_videoplayer;

        //Image
        ImageView iv_image_icon;

        //Gif
        GifImageView iv_image_gif;

        //软件推广
        Button btn_install;

        public ViewHolder(View convertView, int itemType) {
            super(convertView);
            switch (itemType) {
                case TYPE_VIDEO://视频
                    //在这里实例化特有的
                    tv_play_nums = (TextView) convertView.findViewById(R.id.tv_play_nums);
                    tv_video_duration = (TextView) convertView.findViewById(R.id.tv_video_duration);
                    iv_commant = (ImageView) convertView.findViewById(R.id.iv_commant);
                    tv_commant_context = (TextView) convertView.findViewById(R.id.tv_commant_context);
                    jcv_videoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
                    break;
                case TYPE_IMAGE://图片
                    iv_image_icon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
                    break;
                case TYPE_TEXT://文字
                    break;
                case TYPE_GIF://gif
                    iv_image_gif = (GifImageView) convertView.findViewById(R.id.iv_image_gif);
                    break;
                case TYPE_AD://软件广告
                    btn_install = (Button) convertView.findViewById(R.id.btn_install);
                    iv_image_icon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
                    break;
            }

            //公共部分的
            switch (itemType) {
                case TYPE_VIDEO://视频
                case TYPE_IMAGE://图片
                case TYPE_TEXT://文字
                case TYPE_GIF://gif
                    //加载除开广告部分的公共部分视图
                    //user info
                    iv_headpic = (ImageView) convertView.findViewById(R.id.iv_headpic);
                    tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                    tv_time_refresh = (TextView) convertView.findViewById(R.id.tv_time_refresh);
                    iv_right_more = (ImageView) convertView.findViewById(R.id.iv_right_more);
                    //bottom
                    iv_video_kind = (ImageView) convertView.findViewById(R.id.iv_video_kind);
                    tv_video_kind_text = (TextView) convertView.findViewById(R.id.tv_video_kind_text);
                    tv_shenhe_ding_number = (TextView) convertView.findViewById(R.id.tv_shenhe_ding_number);
                    tv_shenhe_cai_number = (TextView) convertView.findViewById(R.id.tv_shenhe_cai_number);
                    tv_posts_number = (TextView) convertView.findViewById(R.id.tv_posts_number);
                    ll_download = (LinearLayout) convertView.findViewById(R.id.ll_download);

                    break;
            }
            //中间公共部分 -所有的都有
            tv_context = (TextView) convertView.findViewById(R.id.tv_context);
        }

    }

}
