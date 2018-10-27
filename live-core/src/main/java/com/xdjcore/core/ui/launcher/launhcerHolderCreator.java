package com.xdjcore.core.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by jx on 2018/5/3.
 */

public class launhcerHolderCreator implements CBViewHolderCreator<launcherHolder> {

    @Override
    public launcherHolder createHolder() {
        return new launcherHolder();
    }



}
