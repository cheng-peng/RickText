package com.cxp.ricktext.span;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.cxp.ricktext.listener.SpanAtUserCallBack;
import com.cxp.ricktext.model.UserModel;

/**
 * 文 件 名: ClickAtUserSpan
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:23
 * 描    述: 显示可点击的@某人
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class ClickAtUserSpan extends ClickableSpan {

    private UserModel userModel;
    private int color;
    private SpanAtUserCallBack spanClickCallBack;

    public ClickAtUserSpan(UserModel userModel,int color, SpanAtUserCallBack spanClickCallBack) {
        this.userModel = userModel;
        this.color = color;
        this.spanClickCallBack = spanClickCallBack;
    }

    @Override
    public void onClick(View widget) {
        if (spanClickCallBack != null) {
            spanClickCallBack.onClick(widget, userModel);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        /** 给文字染色 **/
        ds.setColor(color);
        /** 去掉下划线 ， 默认自带下划线 **/
        ds.setUnderlineText(false);
    }
}
