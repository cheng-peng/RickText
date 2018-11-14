package com.cxp.ricktext;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cxp.ricktext.listener.OnEditTextUtilJumpListener;
import com.cxp.ricktext.listener.SpanAtUserCallBack;
import com.cxp.ricktext.listener.SpanTopicCallBack;
import com.cxp.ricktext.listener.SpanUrlCallBack;
import com.cxp.ricktext.model.TopicModel;
import com.cxp.ricktext.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RichEditText mRichEditText;
    private RichTextView mRichTextView;

    private List<TopicModel> topicModels = new ArrayList<>();
    private List<UserModel> nameList = new ArrayList<>();

    private List<TopicModel> topicModelsEd = new ArrayList<>();
    private  List<UserModel> nameListEd = new ArrayList<>();

    public final static int REQUEST_USER_CODE_INPUT = 1;
    public final static int REQUEST_TOPIC_CODE_INPUT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRichEditText= findViewById(R.id.main_et);
        mRichTextView = findViewById(R.id.main_tv);


        //初始化表情
        initEmoji();

        resolveRichShow();

        RichEditBuilder richEditBuilder = new RichEditBuilder();
        richEditBuilder.setEditText(mRichEditText)
                .setTopicModels(topicModelsEd)
                .setUserModels(nameListEd)
                .setColorAtUser("#FF00C0")
                .setColorTopic("#FF0000")
                .setEditTextAtUtilJumpListener(new OnEditTextUtilJumpListener() {
                    @Override
                    public void notifyAt() {
                        JumpUtil.goToUserList(MainActivity.this, MainActivity.REQUEST_USER_CODE_INPUT);
                    }

                    @Override
                    public void notifyTopic() {
                        JumpUtil.goToTopicList(MainActivity.this, MainActivity.REQUEST_TOPIC_CODE_INPUT);
                    }
                })
                .builder();

    }

    private void initData() {
        nameList.clear();
        topicModels.clear();
        UserModel userModel = new UserModel();
        userModel.setUser_name("某个人");
        userModel.setUser_id("某个人");
        nameList.add(userModel);
        userModel = new UserModel();
        userModel.setUser_name("22222");
        userModel.setUser_id("2222");
        nameList.add(userModel);
        userModel = new UserModel();
        userModel.setUser_name("kkk");
        userModel.setUser_id("23333");
        nameList.add(userModel);

        TopicModel topicModel = new TopicModel();
        topicModel.setTopicId("333");
        topicModel.setTopicName("话题话题");
        topicModels.add(topicModel);

    }


    private void resolveRichShow() {

        initData();

        String content = "这是测试#话题话题#文本哟 www.baidu.com " +
                "来@某个人  @22222 @kkk " +
                "好的,来几个表情[e2][e4][e55]，最后来一个电话昂昂昂 13245685478";
        initTextView(content);
    }

    private void initTextView(String content) {
        //直接使用mRichTextView
        mRichTextView.setAtColor(Color.RED);
        mRichTextView.setTopicColor(Color.BLUE);
        mRichTextView.setLinkColor(Color.YELLOW);
        mRichTextView.setNeedNumberShow(true);
        mRichTextView.setNeedUrlShow(true);
        mRichTextView.setSpanAtUserCallBackListener(spanAtUserCallBack);
        mRichTextView.setSpanTopicCallBackListener(spanTopicCallBack);
        mRichTextView.setSpanUrlCallBackListener(spanUrlCallBack);
        //所有配置完成后才设置text
        mRichTextView.setRichText(content, nameList, topicModels);
    }


    /**
     * 处理自己的表情
     */
    private void initEmoji() {
        List<Integer> data = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        for (int i = 1; i < 64; i++) {
            int resId = getResources().getIdentifier("e" + i, "drawable", getPackageName());
            data.add(resId);
            strings.add("[e" + i + "]");
        }
        /**初始化为自己的**/
        SmileUtils.addPatternAll(SmileUtils.getEmoticons(), strings, data);
    }

    private SpanUrlCallBack spanUrlCallBack = new SpanUrlCallBack() {
        @Override
        public void phone(View view, String phone) {
            Toast.makeText(view.getContext(), phone + " 被点击了", Toast.LENGTH_SHORT).show();
            if (view instanceof TextView) {
                ((TextView) view).setHighlightColor(Color.TRANSPARENT);
            }
        }

        @Override
        public void url(View view, String url) {
            Toast.makeText(view.getContext(), url + " 被点击了", Toast.LENGTH_SHORT).show();
            if (view instanceof TextView) {
                ((TextView) view).setHighlightColor(Color.TRANSPARENT);
            }
        }
    };

    private SpanAtUserCallBack spanAtUserCallBack = new SpanAtUserCallBack() {
        @Override
        public void onClick(View view, UserModel userModel1) {
            Toast.makeText(view.getContext(), userModel1.getUser_name() + " 被点击了", Toast.LENGTH_SHORT).show();
            if (view instanceof TextView) {
                ((TextView) view).setHighlightColor(Color.TRANSPARENT);
            }
        }
    };

    private SpanTopicCallBack spanTopicCallBack = new SpanTopicCallBack() {
        @Override
        public void onClick(View view, TopicModel topicModel) {
            Toast.makeText(view.getContext(), topicModel.getTopicName() + " 被点击了", Toast.LENGTH_SHORT).show();
            if (view instanceof TextView) {
                ((TextView) view).setHighlightColor(Color.TRANSPARENT);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_USER_CODE_INPUT:
                    mRichEditText.resolveAtResultByEnterAt((UserModel) data.getSerializableExtra(UserListActivity.DATA));
                    break;

                case REQUEST_TOPIC_CODE_INPUT:
                    mRichEditText.resolveTopicResultByEnter((TopicModel) data.getSerializableExtra(TopicListActivity.DATA));
                    break;
            }
        }

    }

}
