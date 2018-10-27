package com.xdjcore.core.ui.recycler;

import android.support.v7.widget.GridLayoutManager;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xdjcore.core.R;

import java.util.List;

/**
 * Created by jx on 2018/5/8.
 */

public class MultipleRecycleAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements OnItemClickListener, BaseQuickAdapter.SpanSizeLookup {

    protected MultipleRecycleAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化，设置不同ITEM的布局
        init();
    }

    private void init() {
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_text_img);
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);

    }

    public static MultipleRecycleAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecycleAdapter(data);
    }

    public static MultipleRecycleAdapter create(DataConverter converter) {
        return new MultipleRecycleAdapter(converter.convert());
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        switch (helper.getItemViewType()) {
            case ItemType.TEXT_IMAGE:

                break;

        }


    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
