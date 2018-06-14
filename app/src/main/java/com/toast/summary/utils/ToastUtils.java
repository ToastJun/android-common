package com.toast.summary.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.Toast;

public final class ToastUtils {

    private static final int COLOR_DEFAULT = 0xFEFFFFFF;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    private static Toast sToast;
    private static int sGravity;
    private static int sXOffset = -1;
    private static int sYOffset = -1;
    private static int sBgColor = COLOR_DEFAULT;
    private static int sBgResource = -1;
    private static int sMsgColor = COLOR_DEFAULT;

    private ToastUtils(){
        throw new UnsupportedOperationException("can't instantiate me...");
    }

    /**
     * set the gravity
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static void setGravity(int gravity, int xOffset, int yOffset) {
        sGravity = gravity;
        sXOffset = xOffset;
        sYOffset = yOffset;
    }

    public static void setBgColor(@ColorInt int backgroundColor) {
        sBgColor = backgroundColor;
    }

    public static void setBgResource(@DrawableRes int bgResource) {
        sBgResource = bgResource;
    }

    public static void setMsgColor(@ColorInt int msgColor) {
        sMsgColor = msgColor;
    }

    public static void showShort(@NonNull CharSequence text) {

    }
}
