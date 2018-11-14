package com.cxp.ricktext.span;

import android.text.TextPaint;
import android.view.View;

import com.cxp.ricktext.listener.SpanTopicCallBack;
import com.cxp.ricktext.model.TopicModel;

/**
 * 文 件 名: ClickTopicSpan
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:22
 * 描    述: 话题#点击回调
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class ClickTopicSpan extends ClickAtUserSpan {

    private TopicModel topicModel;
    private int color;
    private SpanTopicCallBack spanTopicCallBack;

    public ClickTopicSpan(TopicModel topicModel, int color, SpanTopicCallBack spanTopicCallBack) {
        super(null, color, null);
        this.topicModel = topicModel;
        this.color = color;
        this.spanTopicCallBack = spanTopicCallBack;
    }

    @Override
    public void onClick(View widget) {
        if (spanTopicCallBack != null) {
            spanTopicCallBack.onClick(widget, topicModel);
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
