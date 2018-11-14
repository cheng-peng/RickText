package com.cxp.ricktext.span;

import android.content.Context;

import com.cxp.ricktext.listener.SpanAtUserCallBack;
import com.cxp.ricktext.listener.SpanTopicCallBack;
import com.cxp.ricktext.listener.SpanUrlCallBack;
import com.cxp.ricktext.model.TopicModel;
import com.cxp.ricktext.model.UserModel;

/**
 * 文 件 名: SpanCreateListener
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:37
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface SpanCreateListener {

    ClickAtUserSpan getCustomClickAtUserSpan(Context context, UserModel userModel, int color, SpanAtUserCallBack spanClickCallBack);

    ClickTopicSpan getCustomClickTopicSpan(Context context, TopicModel topicModel, int color, SpanTopicCallBack spanTopicCallBack);

    LinkSpan getCustomLinkSpan(Context context, String url, int color, SpanUrlCallBack spanUrlCallBack);
}
