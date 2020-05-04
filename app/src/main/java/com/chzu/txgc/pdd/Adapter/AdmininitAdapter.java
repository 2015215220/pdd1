package com.chzu.txgc.pdd.Adapter;

import android.content.Context;
import android.view.View;

import com.chzu.txgc.pdd.Bean.AdminBean;
import com.chzu.txgc.pdd.R;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

public class AdmininitAdapter extends EasyRVAdapter<AdminBean> {
    public AdmininitAdapter(Context context, List<AdminBean> list, int... layoutIds) {
        super(context, list, layoutIds);//需要生成的方法
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, AdminBean item) {
        viewHolder.setText(R.id.telephone,item.getTelephone())
                  .setText(R.id.fj,"查看详情");
//        viewHolder.setOnClickListener(R.id.telephone, (View.OnClickListener) this);
    }
}
