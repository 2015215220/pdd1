package com.chzu.txgc.pdd.OtherAddActivity;



import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chzu.txgc.pdd.Activity.BaseActivity;
import com.chzu.txgc.pdd.Bean.AdminBean;
import com.chzu.txgc.pdd.Bean.PersoninformationBean;
import com.chzu.txgc.pdd.Contact.CheckSumBuilder;
import com.chzu.txgc.pdd.Implement.OnRequestResult;
import com.chzu.txgc.pdd.R;
import com.chzu.txgc.pdd.Utils.JsonUtils;
import com.chzu.txgc.pdd.Utils.OkgoUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.netease.nim.uikit.common.ToastHelper;
import com.netease.nim.uikit.common.util.sys.NetworkUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminfengActivity extends BaseActivity implements OnRequestResult {
    List<PersoninformationBean> personinformationBean;
    AdminBean adminBean;//获取数据源
    private Button fjzh;//获取封禁账号
    private Button jjzh;//获取解禁账号
    private TextView nichname;//昵称
    private TextView telephone;//电话

    @Override
    public int bindLayout() {
        return R.layout.activity_adminfeng;
    }

    @Override
    protected void findViews() {
        Intent intent = getIntent();
        fjzh=findViewById(R.id.fjzh);//获取封禁账号
        jjzh=findViewById(R.id.jjzh);//获取解禁账号
        nichname=findViewById(R.id.nichname);//昵称
        telephone=findViewById(R.id.telephone);//电话
        adminBean = (AdminBean) intent.getSerializableExtra("adminbean");
        telephone.setText(adminBean.getTelephone());
        //通过电话找到昵称
        gerenxinxi(adminBean.getTelephone());
    }
    @Override
    protected void initListeners() {
        fjzh.setOnClickListener(this);//获取封禁账号
        jjzh.setOnClickListener(this);//获取解禁账号
    }
    @Override
    protected void initData() {
    }
    @Override
    protected void onViewClicked(int viewId) {
      switch (viewId){
          case R.id.fjzh:
              fjzh_information(telephone.getText().toString());
              break;
          case R.id.jjzh:
              jjzh_information(telephone.getText().toString());

              break;
      }
    }
    private void jjzh_information(String telephone) {
        if (!NetworkUtil.isNetAvailable(this)) {
            ToastHelper.showToast(this, R.string.network_is_not_available);
            return;
        }
        String url = "https://api.netease.im/nimserver/user/unblock.action";
        HttpHeaders headers = new HttpHeaders();
        String appKey = "1ab863550523720e07fad8ed130b4e5c";
        String appSecret = "41c25d684abc";
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        headers.put("appkey", appKey);
        headers.put("Nonce", nonce);
        headers.put("CurTime", curTime);
        headers.put("CheckSum", checkSum);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        Map<String, String> params = new HashMap<>();
        params.put("accid",  telephone);//["zhangsan"] 变态的参数说明[18855054359]
        params.put("needkick","true");//
        OkgoUtils.getInstance().post(url, params, headers, 35, AdminfengActivity.this);
    }

    private void fjzh_information(String telephone) {
        if (!NetworkUtil.isNetAvailable(this)) {
            ToastHelper.showToast(this, R.string.network_is_not_available);
            return;
        }
        String url = "https://api.netease.im/nimserver/user/block.action";
        HttpHeaders headers = new HttpHeaders();
        String appKey = "1ab863550523720e07fad8ed130b4e5c";
        String appSecret = "41c25d684abc";
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        headers.put("appkey", appKey);
        headers.put("Nonce", nonce);
        headers.put("CurTime", curTime);
        headers.put("CheckSum", checkSum);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        Map<String, String> params = new HashMap<>();
        params.put("accid",  telephone);//["zhangsan"] 变态的参数说明[18855054359]
        params.put("needkick","true");//
        OkgoUtils.getInstance().post(url, params, headers, 34, AdminfengActivity.this);

    }

    private void gerenxinxi(String telephone) {
        if (!NetworkUtil.isNetAvailable(this)) {
            ToastHelper.showToast(this, R.string.network_is_not_available);
            return;
        }
        String url = "https://api.netease.im/nimserver/user/getUinfos.action";
        HttpHeaders headers = new HttpHeaders();
        String appKey = "1ab863550523720e07fad8ed130b4e5c";
        String appSecret = "41c25d684abc";
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        headers.put("appkey", appKey);
        headers.put("Nonce", nonce);
        headers.put("CurTime", curTime);
        headers.put("CheckSum", checkSum);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        Map<String, String> params = new HashMap<>();
        params.put("accids", "["+ telephone+"]");//["zhangsan"] 变态的参数说明[18855054359]
        OkgoUtils.getInstance().post(url, params, headers, 33, AdminfengActivity.this);
    }

    @Override
    public void onSuccess(int code, String json) {
        switch (code) {
            case 33:
                //{"code":200,"uinfos":[{"accid":"18855054359","name":"yyy","gender":0}]}  这是数组的形式和{}不一样
                if (JsonUtils.getValue(json, "code").equals("200")) {//成功
                    personinformationBean = JsonUtils.getList(json, "uinfos", PersoninformationBean.class);
                    nichname.setText(personinformationBean.get(0).getName());
                } else {
                    ToastUtils.showShort(JsonUtils.getValue(json, "desc"));
                }
                break;
            case 34:
                if (JsonUtils.getValue(json, "code").equals("200")) {//成功
                    ToastUtils.showShort("账号已经封禁完成");
                }
                break;
            case 35:
                if (JsonUtils.getValue(json, "code").equals("200")) {//成功
                    ToastUtils.showShort("账号已经解禁完成");
                }
                break;
        }
    }

    @Override
    public void onFailed(int code, String msg) {

    }
}
