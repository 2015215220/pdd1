package com.chzu.txgc.pdd.OtherAddActivity;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chzu.txgc.pdd.Activity.BaseActivity;
import com.chzu.txgc.pdd.R;
public class Agree2Activity extends BaseActivity {
    EditText feedback;//意见反馈的内容
    Button submit;//提交   必须是4G网
    @Override
    public int bindLayout() {
        return R.layout.activity_agree2;
    }
    @Override
    protected void findViews() {
        feedback=findViewById(R.id.feedback);
        submit=findViewById(R.id.submit);
    }
    @Override
    protected void initListeners() {
        submit.setOnClickListener(this);
    }
    @Override
    protected void initData() {
    }
    @Override
    protected void onViewClicked(int viewId){
        switch (viewId){
            case R.id.submit:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sendMail();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
    private void sendMail() {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        Session session = Session.getInstance(properties);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("2524394464@qq.com"));//发件人的号  小号
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("1654348717@qq.com"));//一个收件人  大号
            message.setSubject("意见反馈");// 设置邮件标题
            message.setText("收到用户"+SPUtils.getInstance().getString("phone_value")+"意见反馈内容为:"+feedback.getText().toString());// 设置邮件内容  带一下当前用户的名字
            Transport transport = session.getTransport();// 得到邮差对象
            transport.connect("2524394464@qq.com", "hyrclpegipegebba");// 连接自己的邮箱账户  密码为QQ邮箱开通的stmp服务后得到的客户端授权码
            transport.sendMessage(message, message.getAllRecipients());// 发送邮件
            ToastUtils.showShort("发送邮件成功");
            LogUtils.e("hy","发送邮件成功");
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
