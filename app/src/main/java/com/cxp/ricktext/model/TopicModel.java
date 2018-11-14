package com.cxp.ricktext.model;

import java.io.Serializable;

/**
 * 文 件 名: TopicModel
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:13
 * 描    述: 话题model
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class TopicModel implements Serializable {
    /**
     * 话题名字内部不能有#和空格
     */
    private String topicName;
    private String topicId;

    public TopicModel() {

    }

    public TopicModel(String topicName, String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return this.topicName;
    }
}
