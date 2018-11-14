package com.cxp.ricktext;

import android.app.Activity;
import android.content.Intent;

/**
 * 文 件 名: JumpUtil
 * 创 建 人: CXP
 * 创建日期: 2018-10-15 16:15
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class JumpUtil {

    public static void goToUserList(Activity activity, int code) {
        Intent intent = new Intent(activity, UserListActivity.class);
        activity.startActivityForResult(intent, code);
    }


    public static void goToTopicList(Activity activity, int code) {
        Intent intent = new Intent(activity, TopicListActivity.class);
        activity.startActivityForResult(intent, code);
    }
}
