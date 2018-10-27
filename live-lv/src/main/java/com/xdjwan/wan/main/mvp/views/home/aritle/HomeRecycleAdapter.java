package com.xdjwan.wan.main.mvp.views.home.aritle;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Html;
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
import com.xdjwan.wan.main.mvp.views.home.AritleSortFragment;
import com.xdjwan.wan.main.mvp.views.person.login.LoginFragment;

import java.util.List;

public class HomeRecycleAdapter extends BaseQuickAdapter<MultipleItemEntity, BaseViewHolder> {
    private WanAdFragment DELEGATE;

    public HomeRecycleAdapter(int layoutResId, @Nullable List<MultipleItemEntity> data, WanAdFragment delegate) {
        super(layoutResId, data);
        DELEGATE = delegate;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void convert(final BaseViewHolder helper, final MultipleItemEntity item) {
        final IconTextView tv_collect = helper.getView(R.id.id_home_item_collect);
        TextView tv_title = helper.getView(R.id.id_home_item_title);
        TextView tv_author = helper.getView(R.id.id_home_item_author);
        TextView tv_time = helper.getView(R.id.id_home_item_time);
        TextView tv_superChapterName = helper.getView(R.id.id_home_item_superChapterName);
        TextView tv_chapterName = helper.getView(R.id.id_home_item_chapterName);
        helper.addOnClickListener(R.id.id_home_item_superChapterName);
        helper.addOnClickListener(R.id.id_home_item_chapterName);
        helper.addOnClickListener(R.id.id_collect);
        //
        boolean isCollect = item.getField(AritleDatasMultipleFields.COLLECT);
        if (isCollect) {
            tv_collect.setTextColor(Color.RED);
        } else {
            tv_collect.setTextColor(Color.GRAY);
        }


        //
        String author = item.getField(AritleDatasMultipleFields.AUTHOR);
        String title = item.getField(AritleDatasMultipleFields.TITLE);
        String time = item.getField(AritleDatasMultipleFields.NICEDATE);
        String superChapterName = item.getField(AritleDatasMultipleFields.SUPERCHAPTERNAME);
        String chapterName = item.getField(AritleDatasMultipleFields.CHAPTERNAME);

        tv_author.setText(author);
        tv_title.setText(Html.fromHtml(title));
        tv_time.setText(time);
        tv_superChapterName.setText(superChapterName);
        tv_chapterName.setText(chapterName);

        //设置ITem的view点击事件
        setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {

                int i = view.getId();
                if (i == R.id.id_home_item_superChapterName) {
                    //打开新的fragment，传入页码和superChapterID
                    int superChapterID = ((MultipleItemEntity) adapter.getData().get(position)).getField(AritleDatasMultipleFields.SUPERCHAPTERID);
                    DELEGATE.getSupportDelegate().start(AritleSortFragment.create(0, superChapterID));
                } else if (i == R.id.id_home_item_chapterName) {
                    //打开新的fragment，传入页码和chapterID
                    int chapterID = ((MultipleItemEntity) adapter.getData().get(position)).getField(AritleDatasMultipleFields.CHAPTERID);
                    DELEGATE.getSupportDelegate().start(AritleSortFragment.create(0, chapterID));

                } else if (i == R.id.id_collect) {
                    //处理收藏
                    final MultipleItemEntity multipleItemEntity = (MultipleItemEntity) adapter.getData().get(position);
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
                                        // tv_collect.setBackgroundColor(Color.RED);
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
                                    } else {
                                        Toast.makeText(DELEGATE.getContext(), "取消失败:" + msg, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    } else {
                        //转跳到登录界面
                        DELEGATE.getSupportDelegate().start(LoginFragment.newInstance());

                    }
                }
            }
        });

    }


}


