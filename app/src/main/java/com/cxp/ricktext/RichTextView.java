package com.cxp.ricktext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.text.DynamicLayout;
import android.text.StaticLayout;
import android.text.style.DynamicDrawableSpan;
import android.util.AttributeSet;
import android.view.View;

import com.cxp.ricktext.listener.SpanAtUserCallBack;
import com.cxp.ricktext.listener.SpanTopicCallBack;
import com.cxp.ricktext.listener.SpanUrlCallBack;
import com.cxp.ricktext.model.TopicModel;
import com.cxp.ricktext.model.UserModel;
import com.cxp.ricktext.span.SpanCreateListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: RichTextView
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 10:01
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class RichTextView extends AppCompatTextView {

    private boolean needNumberShow = true;//是否需要数字处理
    private boolean needUrlShow = true;//是否需要url处理
    private int atColor = Color.BLUE;  //@ 颜色
    private int topicColor = Color.BLUE; //话题 颜色
    private int linkColor = Color.BLUE;  //连接 颜色
    private int emojiSize = 0;  //表情大小
    private int emojiVerticalAlignment = DynamicDrawableSpan.ALIGN_BOTTOM;//垂直方式

    private SpanUrlCallBack spanUrlCallBackListener;
    private SpanAtUserCallBack spanAtUserCallBackListener;
    private SpanTopicCallBack spanTopicCallBackListener;
    private SpanCreateListener spanCreateListener;

    private List<TopicModel> topicList = new ArrayList<>();

    private List<UserModel> nameList = new ArrayList<>();

    private RichTextView mTextView;


    public RichTextView(Context context) {
        this(context,null);
    }

    public RichTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RichTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    //初始化配置
    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {

            mTextView=this;

            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RichTextView);
            needNumberShow = array.getBoolean(R.styleable.RichTextView_needNumberShow, false);
            needUrlShow = array.getBoolean(R.styleable.RichTextView_needUrlShow, false);
            atColor = array.getColor(R.styleable.RichTextView_atColor, Color.BLUE);
            topicColor = array.getColor(R.styleable.RichTextView_topicColor, Color.BLUE);
            linkColor = array.getColor(R.styleable.RichTextView_linkColor, Color.BLUE);
            emojiSize = array.getInteger(R.styleable.RichTextView_emojiSize, 0);
            emojiVerticalAlignment = array.getInteger(R.styleable.RichTextView_emojiVerticalAlignment, DynamicDrawableSpan.ALIGN_BOTTOM);
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        StaticLayout layout = null;
        Field field = null;
        try {
            Field staticField = DynamicLayout.class.getDeclaredField("sStaticLayout");
            staticField.setAccessible(true);
            layout = (StaticLayout) staticField.get(DynamicLayout.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (layout != null) {
            try {
                field = StaticLayout.class.getDeclaredField("mMaximumVisibleLineCount");
                field.setAccessible(true);
                field.setInt(layout, getMaxLines());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (layout != null && field != null) {
            try {
                field.setInt(layout, Integer.MAX_VALUE);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private SpanUrlCallBack spanUrlCallBack = new SpanUrlCallBack() {
        @Override
        public void phone(View view, String phone) {
            if (spanUrlCallBackListener != null) {
                spanUrlCallBackListener.phone(view, phone);
            }
        }

        @Override
        public void url(View view, String url) {
            if (spanUrlCallBackListener != null) {
                spanUrlCallBackListener.url(view, url);
            }
        }
    };

    private SpanAtUserCallBack spanAtUserCallBack = new SpanAtUserCallBack() {
        @Override
        public void onClick(View view, UserModel userModel1) {
            if (spanAtUserCallBackListener != null) {
                spanAtUserCallBackListener.onClick(view, userModel1);
            }
        }
    };

    private SpanTopicCallBack spanTopicCallBack = new SpanTopicCallBack() {
        @Override
        public void onClick(View view, TopicModel topicModel) {
            if (spanTopicCallBackListener != null) {
                spanTopicCallBackListener.onClick(view, topicModel);
            }
        }
    };

    /**
     * 显示处理文本
     *
     * @param content
     */
    private void resolveRichShow(String content) {
        RichTextBuilder richTextBuilder = new RichTextBuilder(getContext());
        richTextBuilder.setContent(content)
                .setAtColor(atColor)
                .setLinkColor(linkColor)
                .setTopicColor(topicColor)
                .setListUser(nameList)
                .setListTopic(topicList)
                .setNeedNum(needNumberShow)
                .setNeedUrl(needUrlShow)
                .setTextView(this)
                .setEmojiSize(emojiSize)
                .setSpanAtUserCallBack(spanAtUserCallBack)
                .setSpanUrlCallBack(spanUrlCallBack)
                .setSpanTopicCallBack(spanTopicCallBack)
                .setVerticalAlignment(emojiVerticalAlignment)
                .setSpanCreateListener(spanCreateListener)
                .build();

    }

    /**
     * 设置@某人文本
     *
     * @param text     文本
     * @param nameList @人列表
     */
    public void setRichTextUser(String text, List<UserModel> nameList) {
        this.setRichText(text, nameList, topicList);
    }

    /**
     * 设置话题文本
     *
     * @param text      文本
     * @param topicList 话题列表
     */
    public void setRichTextTopic(String text, List<TopicModel> topicList) {
        this.setRichText(text, nameList, topicList);
    }

    /**
     * 设置话题和@文本
     *
     * @param text      文本
     * @param nameList  @人列表
     * @param topicList 话题列表
     */
    public void setRichText(String text, List<UserModel> nameList, List<TopicModel> topicList) {
        if (nameList != null) {
            this.nameList = nameList;
        }
        if (topicList != null) {
            this.topicList = topicList;
        }
        resolveRichShow(text);
    }

    /**
     * 设置话题和@文本
     *
     * @param text 文本
     */
    public void setRichText(String text) {
        setRichText(text, nameList, topicList);
    }

    public boolean isNeedNumberShow() {
        return needNumberShow;
    }

    public List<TopicModel> getTopicList() {
        return topicList;
    }

    /**
     * 设置话题列表
     */
    public void setTopicList(List<TopicModel> topicList) {
        this.topicList = topicList;
    }

    public List<UserModel> getNameList() {
        return nameList;
    }

    /**
     * 设置at列表
     */
    public void setNameList(List<UserModel> nameList) {
        this.nameList = nameList;
    }

    /**
     * 是否需要处理数字
     *
     * @param needNumberShow 是否需要高亮数字和点击
     */
    public void setNeedNumberShow(boolean needNumberShow) {
        this.needNumberShow = needNumberShow;
    }

    public boolean isNeedUrlShow() {
        return needUrlShow;
    }

    /**
     * 是否需要处理数字
     *
     * @param needUrlShow 是否需要高亮url和点击
     */
    public void setNeedUrlShow(boolean needUrlShow) {
        this.needUrlShow = needUrlShow;
    }

    /**
     * url点击
     */
    public void setSpanUrlCallBackListener(SpanUrlCallBack spanUrlCallBackListener) {
        this.spanUrlCallBackListener = spanUrlCallBackListener;
    }

    /**
     * at某人点击
     */
    public void setSpanAtUserCallBackListener(SpanAtUserCallBack spanAtUserCallBackListener) {
        this.spanAtUserCallBackListener = spanAtUserCallBackListener;
    }

    /**
     * 设置自定义span回调
     */
    public void setSpanCreateListener(SpanCreateListener spanCreateListener) {
        this.spanCreateListener = spanCreateListener;
    }

    /**
     * 话题点击
     */
    public void setSpanTopicCallBackListener(SpanTopicCallBack spanTopicCallBackListener) {
        this.spanTopicCallBackListener = spanTopicCallBackListener;
    }

    public int getAtColor() {
        return atColor;
    }

    /**
     * at某人颜色
     */
    public void setAtColor(int atColor) {
        this.atColor = atColor;
    }

    public int getTopicColor() {
        return topicColor;
    }

    /**
     * 话题颜色
     */
    public void setTopicColor(int topicColor) {
        this.topicColor = topicColor;
    }

    public int getLinkColor() {
        return linkColor;
    }

    /**
     * 链接颜色
     */
    public void setLinkColor(int linkColor) {
        this.linkColor = linkColor;
    }


    public void setEmojiSize(int emojiSize) {
        this.emojiSize = emojiSize;
    }


    /**
     * emoji垂直
     */
    public void setEmojiVerticalAlignment(int emojiVerticalAlignment) {
        this.emojiVerticalAlignment = emojiVerticalAlignment;
    }

    public int getEmojiVerticalAlignment() {
        return emojiVerticalAlignment;
    }

}
