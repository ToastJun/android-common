package com.toast.common.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;

import static android.media.ThumbnailUtils.OPTIONS_RECYCLE_INPUT;
import static android.media.ThumbnailUtils.extractThumbnail;

/**
 * @author tutu
 * @time 2018/1/30
 */

public class VideoUtils {
    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime(-1);
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
            }
        }

        if (bitmap == null) return null;

        if (kind == MediaStore.Images.Thumbnails.MINI_KIND) {
            // Scale down the bitmap if it's too large.
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int max = Math.max(width, height);
            if (max > 512) {
                float scale = 512f / max;
                int w = Math.round(scale * width);
                int h = Math.round(scale * height);
                bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
            }
        } else if (kind == MediaStore.Images.Thumbnails.MICRO_KIND) {
            bitmap = extractThumbnail(bitmap,
                    320,
                    320,
                    OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }
}
