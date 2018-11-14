package com.cxp.ricktext.span;

import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import com.cxp.ricktext.listener.SpanUrlCallBack;

/**
 * 文 件 名: LinkSpan
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:21
 * 描    述: 显示可点击的手机号码和URL
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class LinkSpan extends ClickableSpan {

    private String url;
    private int color;
    private SpanUrlCallBack spanUrlCallBack;

    public LinkSpan(String url, int color, SpanUrlCallBack spanUrlCallBack) {
        this.url = url;
        this.color = color;
        this.spanUrlCallBack = spanUrlCallBack;
    }

    @Override
    public void onClick(View widget) {
        if ((url.contains("tel:") && TextUtils.isDigitsOnly(url.replace("tel:", ""))) || TextUtils.isDigitsOnly(url)) {
            if (spanUrlCallBack != null)
                spanUrlCallBack.phone(widget, url);
        } else {
            if (spanUrlCallBack != null)
                spanUrlCallBack.url(widget, url);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(color);
        /** 去掉下划线 ， 默认自带下划线 **/
        ds.setUnderlineText(false);
    }
}
