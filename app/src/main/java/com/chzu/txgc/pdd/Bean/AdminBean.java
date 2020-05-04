package com.chzu.txgc.pdd.Bean;

import java.io.Serializable;

public class AdminBean implements Serializable {
    private String telephone;//电话
    private String password;//密码
    private String nickname;//昵称

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
