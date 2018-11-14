package com.cxp.ricktext.listener;

import android.content.Context;
import android.text.method.MovementMethod;

import com.cxp.ricktext.model.TopicModel;
import com.cxp.ricktext.model.UserModel;
import com.cxp.ricktext.span.ClickAtUserSpan;
import com.cxp.ricktext.span.ClickTopicSpan;
import com.cxp.ricktext.span.LinkSpan;

/**
 * 文 件 名: ITextViewShow
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:54
 * 描    述: 显示接口
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface ITextViewShow {

    void setText(CharSequence charSequence);

    CharSequence getText();

    void setMovementMethod(MovementMethod movementMethod);

    void setAutoLinkMask(int flag);

    ClickAtUserSpan getCustomClickAtUserSpan(Context context, UserModel userModel, int color, SpanAtUserCallBack spanClickCallBack);

    ClickTopicSpan getCustomClickTopicSpan(Context context, TopicModel topicModel, int color, SpanTopicCallBack spanTopicCallBack);

    LinkSpan getCustomLinkSpan(Context context, String url, int color, SpanUrlCallBack spanUrlCallBack);

    int emojiSize();

    int verticalAlignment();
}
