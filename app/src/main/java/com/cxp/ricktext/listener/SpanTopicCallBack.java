package com.cxp.ricktext.listener;

import android.view.View;

import com.cxp.ricktext.model.TopicModel;

/**
 * 文 件 名: SpanTopicCallBack
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:12
 * 描    述: 处理话题的回调
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public interface SpanTopicCallBack {
    void onClick(View view, TopicModel topicModel);
}
