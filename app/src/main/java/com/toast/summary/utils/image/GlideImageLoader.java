package com.toast.summary.utils.image;

import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

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

    private void genericDisplayImage(DrawableTypeRequest request, ImageView imageView, DisplayOptions displayOptions) {
        if (displayOptions != null) {
            request.error(displayOptions.errorResId)
                    .placeholder(displayOptions.loadingResId)
                    .into(imageView);
        } else {
            request.into(imageView);
        }
    }
}
