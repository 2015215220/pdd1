package com.chzu.txgc.pdd.Uikit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chzu.txgc.pdd.R;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.model.main.OnlineStateContentProvider;
import com.netease.nim.uikit.common.activity.ListActivityBase;
import com.netease.nim.uikit.common.ui.imageview.HeadImageView;
import com.netease.nim.uikit.common.ui.recyclerview.holder.BaseViewHolder;
import com.netease.nimlib.sdk.robot.model.NimRobotInfo;

import java.util.List;

/**
 * 机器人列表
 * <p>
 * Created by huangjun on 2017/6/21.
 */

public class RobotListActivity extends ListActivityBase<NimRobotInfo> {

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RobotListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
        LogUtils.e("hy","66666");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtils.e("hy","55555");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected String getTitleString() {
        LogUtils.e("hy","8888");
        return "咨询师";
    }

    @Override
    protected List<NimRobotInfo> onLoadData() {
        LogUtils.e("hy","77777");
        return NimUIKit.getRobotInfoProvider().getAllRobotAccounts();
    }

    @Override
    protected int onItemResId() {
        LogUtils.e("hy","00000");
        return R.layout.nim_robot_list_item;
    }

    @Override
    protected void convertItem(BaseViewHolder helper, NimRobotInfo item) {
        LogUtils.e("hy","9999");
        helper.setText(R.id.robot_name, item.getName());
        OnlineStateContentProvider provider = NimUIKit.getOnlineStateContentProvider();
        TextView textView = helper.getView(R.id.robot_online_state);
        LogUtils.e("hy","44444");
        if (provider == null) {
            textView.setVisibility(View.GONE);
            LogUtils.e("hy","1111");
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(provider.getSimpleDisplay(item.getAccount()));
            LogUtils.e("hy","2222");
        }
        HeadImageView imageView = helper.getView(R.id.robot_avatar);
        imageView.loadAvatar(item.getAvatar());
    }

    @Override
    protected void onItemClick(NimRobotInfo item) {
        RobotProfileActivity.start(this, item.getAccount());
    }
}
