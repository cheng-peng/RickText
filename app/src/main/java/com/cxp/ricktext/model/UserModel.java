package com.cxp.ricktext.model;

import java.io.Serializable;

/**
 * 文 件 名: UserModel
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:14
 * 描    述: 用户model
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class UserModel implements Serializable {

    public UserModel() {

    }

    public UserModel(String user_name, String user_id) {
        this.user_name = user_name;
        this.user_id = user_id;
    }

    /**
     * 名字不能带@和空格
     */
    private String user_name;

    private String user_id;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return this.user_name;
    }
}
