package com.xdjcore.core.bottom;

import com.xdjcore.core.fragments.WanAdFragment;

import java.util.LinkedHashMap;

/**
 * Created by jx on 2018/5/3.
 */

public final class ItemBuilder {
    private final LinkedHashMap<BottomTabBean, WanAdFragment> ITEMS = new LinkedHashMap<>();


    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, WanAdFragment delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, WanAdFragment> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, WanAdFragment> buid() {
        return ITEMS;
    }

}
