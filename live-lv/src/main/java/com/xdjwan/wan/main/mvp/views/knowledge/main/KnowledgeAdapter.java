package com.xdjwan.wan.main.mvp.views.knowledge.main;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xdjwan.wan.R;
import com.xdjwan.wan.main.mvp.models.entity.KnowledgeBean;

import java.util.List;

public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeBean, BaseViewHolder> {

    public KnowledgeAdapter(int layoutResId, @Nullable List<KnowledgeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeBean item) {
        TextView title = helper.getView(R.id.tvTitle);
        TextView content = helper.getView(R.id.tvContent);
        title.setText(item.getName());
        List<KnowledgeBean.ChildrenBean> children = item.getChildren();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < children.size(); i++) {
            stringBuilder.append(children.get(i).getName() + "        ");
        }
        content.setText(stringBuilder.toString());


    }


}
