package com.example.spj.secondplayer.activity;

import android.app.Activity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.secondplayer.R;
import com.example.spj.secondplayer.utils.LogUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ShowPhotoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        String imageUrl = getIntent().getStringExtra("imgUrl");
        String gifUrl = getIntent().getStringExtra("gifUrl");
        boolean isImage = getIntent().getBooleanExtra("isImage", false);
        PhotoView iv_photo = (PhotoView) findViewById(R.id.iv_photo);
        final PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(iv_photo);
        if(imageUrl != null && isImage) {
            LogUtil.e("111111111111111");
            //使用picasso加载图片
            Picasso.with(this)
                    .load(imageUrl)
                    .into(iv_photo, new Callback() {
                        @Override
                        public void onSuccess() {
                            photoViewAttacher.update();
                        }

                        @Override
                        public void onError() {

                        }
                    });

        }


        //使用Glide加载gif图片
        if(gifUrl != null) {
            LogUtil.e("2222222222222");
            Glide.with(this).load(gifUrl).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv_photo);
        }

        LogUtil.e("33333333333333333333");
    }
}
