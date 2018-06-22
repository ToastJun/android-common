package com.toast.summary.utils.image;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * Created by Administrator on 2018/6/4.
 */

public class GlideImageLoader implements ImageLoaderWrapper{

    @Override
    public void displayImage(ImageView imageView, File file, DisplayOptions displayOptions) {
        genericDisplayImage(Glide.with(imageView.getContext()).load(file), imageView, displayOptions);
    }

    @Override
    public void displayImage(ImageView imageView, String url, DisplayOptions displayOptions) {
        genericDisplayImage(Glide.with(imageView.getContext()).load(url), imageView, displayOptions);
    }

    @Override
    public void displayImage(ImageView imageView, String url) {
        displayImage(imageView, url, null);
    }

    private void genericDisplayImage(RequestBuilder<Drawable> request, ImageView imageView, DisplayOptions displayOptions) {
        if (displayOptions != null) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.error(displayOptions.errorResId)
                    .placeholder(displayOptions.loadingResId);
            request.apply(requestOptions).into(imageView);
        } else {
            request.into(imageView);
        }
    }
}
