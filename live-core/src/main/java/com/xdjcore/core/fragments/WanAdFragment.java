package com.xdjcore.core.fragments;

/**
 * Created by jx on 2018/5/3.
 */

public abstract class WanAdFragment extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends WanAdFragment> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
