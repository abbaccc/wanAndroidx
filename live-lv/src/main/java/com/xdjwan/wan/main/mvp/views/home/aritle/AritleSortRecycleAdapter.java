package com.xdjwan.wan.main.mvp.views.home.aritle;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.joanzapata.iconify.widget.IconTextView;
import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjcore.core.ui.recycler.MultipleItemEntity;
import com.xdjwan.wan.R;
import com.xdjwan.wan.datas.Constant;
import com.xdjwan.wan.main.mvp.views.person.login.LoginFragment;

import java.util.List;

public class AritleSortRecycleAdapter extends BaseQuickAdapter<MultipleItemEntity, BaseViewHolder> {
    private WanAdFragment DELEGATE;

    public AritleSortRecycleAdapter(int layoutResId, @Nullable List<MultipleItemEntity> data, WanAdFragment delegate) {
        super(layoutResId, data);
        this.DELEGATE = delegate;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void convert(final BaseViewHolder helper, final MultipleItemEntity item) {
        final IconTextView iconTextView = helper.getView(R.id.id_home_item_collect);
        TextView tv_title = helper.getView(R.id.id_home_item_title);
        TextView tv_author = helper.getView(R.id.id_home_item_author);
        TextView tv_time = helper.getView(R.id.id_home_item_time);
        helper.addOnClickListener(R.id.id_collect);
        //
        String author = item.getField(AritleDatasMultipleFields.AUTHOR);
        String title = item.getField(AritleDatasMultipleFields.TITLE);
        String time = item.getField(AritleDatasMultipleFields.NICEDATE);

        tv_author.setText(author);
        tv_title.setText(title);
        tv_time.setText(time);
        boolean isCollect = item.getField(AritleDatasMultipleFields.COLLECT);
        if (isCollect) {
            iconTextView.setTextColor(Color.RED);
        } else {
            iconTextView.setTextColor(Color.GRAY);
        }


        setOnItemChildClickListener(new OnItemChildClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                final MultipleItemEntity multipleItemEntity = (MultipleItemEntity) adapter.getData().get(position);
                if (view.getId() == R.id.id_collect) {
                    if (SPUtils.getInstance(Constant.LOGIN_SPNAME).getBoolean(Constant.LOGIN_IS)) {
                        boolean isCollect = multipleItemEntity.getField(AritleDatasMultipleFields.COLLECT);
                        if (!isCollect) {
                            //执行同步操作
                            Collect.create().collectAritle((Integer) multipleItemEntity.getField(AritleDatasMultipleFields.ID), new Collect.ICollect() {
                                @Override
                                public void isCollect(boolean is, String msg) {
                                    if (is) {
                                        multipleItemEntity.setField(AritleDatasMultipleFields.COLLECT, true);
                                        notifyItemChanged(position);

                                        //  iconTextView.setBackgroundColor(Color.RED);
                                    } else {
                                        Toast.makeText(DELEGATE.getContext(), "收藏失败:" + msg, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            //取消同步
                            Collect.create().unCollectAritle((Integer) multipleItemEntity.getField(AritleDatasMultipleFields.ID), new Collect.ICollect() {
                                @Override
                                public void isCollect(boolean is, String msg) {
                                    if (is) {
                                        multipleItemEntity.setField(AritleDatasMultipleFields.COLLECT, false);
                                        notifyItemChanged(position);
                                        //iconTextView.setBackgroundColor(Color.GRAY);
                                    } else {
                                        Toast.makeText(DELEGATE.getContext(), "取消失败:" + msg, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    } else {
                        //转跳到登录界面
                        DELEGATE.getParentDelegate().getSupportDelegate().start(LoginFragment.newInstance());

                    }
                }
            }
        });

    }


}


