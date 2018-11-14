package com.cxp.ricktext.listener;

import android.view.View;

import com.cxp.ricktext.model.UserModel;

/**
 * 文 件 名: SpanAtUserCallBack
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:15
 * 描    述: 处理@被某人的回调
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface SpanAtUserCallBack {
    void onClick(View view, UserModel userModel1);
}
