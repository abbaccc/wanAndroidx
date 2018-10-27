package com.xdjwan.wan.main.mvp.views.home.history;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xdjcore.core.fragments.WanAdFragment;
import com.xdjwan.wan.R;
import com.xdjwan.wan.main.mvp.views.home.AritleSearchFragment;

import java.util.List;

public class HistoryDataAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private WanAdFragment skylarkDelegate;

    public HistoryDataAdapter(int layoutResId, @Nullable List<String> data, WanAdFragment delegate) {
        super(layoutResId, data);
        skylarkDelegate = delegate;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tv_name = helper.getView(R.id.id_item_text_history);
        tv_name.setText(item);
        helper.addOnClickListener(R.id.id_rl_histort_satrt);
        helper.addOnClickListener(R.id.id_rl_histort_close);
        setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.id_rl_histort_close) {
                    Log.e("position", position + "");
                    //清除对应数据
                    HistoryUtil.delete(adapter.getData().get(position).toString());
                    setNewData(HistoryUtil.getSearchHistory());
                } else if (view.getId() == R.id.id_rl_histort_satrt) {
                    String name = adapter.getData().get(position).toString();
                    HistoryUtil.saveSearchHistory(name);
                    setNewData(HistoryUtil.getSearchHistory());
                    skylarkDelegate.getSupportDelegate().start(AritleSearchFragment.create(name));
                }
            }
        });

    }


}
