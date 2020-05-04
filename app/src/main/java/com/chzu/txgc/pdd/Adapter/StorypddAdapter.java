package com.chzu.txgc.pdd.Adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chzu.txgc.pdd.Bean.StorypddBean;
import com.chzu.txgc.pdd.R;

import java.util.List;
import androidx.annotation.Nullable;

public class StorypddAdapter extends BaseQuickAdapter<StorypddBean, BaseViewHolder> {
    public StorypddAdapter(int layoutResId, @Nullable List<StorypddBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, StorypddBean item) {
        TextView story_content=helper.getView(R.id.story_content);  //内容
        TextView author=helper.getView(R.id.author);  //作者
        TextView yybb=helper.getView(R.id.yybb);//语音播放
        story_content.setText(item.getContent());//内容
        author.setText(item.getName());//作者
        yybb.setText("语音播放");
        helper.addOnClickListener(R.id.yybb,R.id.story_content);//添加单击事件 不添加则报错误

    }
}
