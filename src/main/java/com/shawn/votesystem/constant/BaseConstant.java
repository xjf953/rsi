package com.shawn.votesystem.constant;

public class BaseConstant {
    public static String REDIS_TOPIC = "vote";

    public static String TELETHON_PIN = "/pin?groupName=%s";

    public static String TELETHON_MEMBERS = "/members?groupName=%s";

    public static Integer PROJECT_STATUS_ACTIVATION = 0;

    public static Integer PROJECT_STATUS_CLOSE = 2;

    public static Integer PROJECT_STATUS_APPROVAL = 1;

    public static String REDIS_MEMBERS_KEY = "vote:telethon:members:";

    public static String REDIS_PIN_KEY = "vote:telethon:pin:";

    public static String BASE_PATH = "/vote/banner/";

    /** banner 状态：激活*/
    public static int BANNER_STATUS_ACTIVATION = 0;

    /** banner 状态：关闭*/
    public static int BANNER_STATUS_CLOSE = 1;



    /** ======================错误码====================*/

    /** 项目ID为空*/
    public static int VOTE_ERROR_CODE_PROJECT_ID = 10000;

    /** 用户名不存在*/
    public static int VOTE_ERROR_CODE_USERNAME = 10001;

    /** 密码错误*/
    public static int VOTE_ERROR_CODE_PASSWORD = 10002;


}
