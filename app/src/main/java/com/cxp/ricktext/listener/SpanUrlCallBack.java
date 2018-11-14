package com.cxp.ricktext.listener;

import android.view.View;

/**
 * 文 件 名: SpanUrlCallBack
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:12
 * 描    述: url被点击的回掉
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface SpanUrlCallBack {
    void phone(View view, String phone);

    void url(View view, String url);
}
