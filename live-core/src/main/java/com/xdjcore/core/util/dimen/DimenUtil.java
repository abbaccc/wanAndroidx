package com.xdjcore.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.xdjcore.core.app.Skylark;

/**
 * Created by xdj on 2018/1/30.
 */

public class DimenUtil {
    public static int getScreenWidth() {
        final Resources resources = Skylark.getApplicationContext().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Skylark.getApplicationContext().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }
}
