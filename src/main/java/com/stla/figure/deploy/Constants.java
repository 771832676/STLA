package com.stla.figure.deploy;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
public class Constants {

    //  全局配置
    public final static int False = 0;
    public final static int True = 1;

    public final static String kCode_Success = "0";          //  成功
    public final static String kCode_Fail = "1";          //  失败
    public final static String kCode_UnAuth = "2000";       //  没有权限
    public final static String kCode_SessionError = "2001";       //  登陆超时
    public final static String kCode_AccountError = "2002";       //  企业用户不存在

    public final static String kDefaultTimeZone = "GMT+8";      //  默认时区

    public final static String kPrefix_Module = "MOD";
    public final static String kPrefix_API = "API";
    public final static String kPrefix_WX = "WX";
    public final static String kPrefix_APP = "APP";

    //  后台管理系统单页记录数量
    public final static int kAdmin_PageSize = 20;
    public final static int kApi_PageSize = 2;

    //  Token秘钥
    public final static String kToken_Secret = "waNgtIansOft";
    //  接口token缓存
    public static final long TOKEN_EXPIRES_DAY = 7;


    public static String REDIS_USER_LIVE_CURRENT_ADDRESS = "REDIS_USER_LIVE_CURRENT_ADDRESS_%s";
    public static String REDIS_CURRENT_LIVE_LIST = "REDIS_CURRENT_LIVE_LIST";

    //  认证信息
    public static final String kAuth_tokenResult = "tokenResult";
    public static final String kAuth_xAccessToken = "x-access-token";

    //用户微信登录
    public static final String   APP_ID ="appid";
    public static final String   OPEN_ID ="openId";
    public static final String   USER_IO ="userIO";

    public static final String SECRET = "8dc77ad94ec8b3ea7b7ed53079857c7d";

    //  cookie保存
    public static final String kHashCookie = "kHashCookie";
    public static final String kCookie_Spokeo = "spokeo";
    public static final String kCookie_icris = "icris";
    public static final String kCookie_neighborwho = "neighborwho";
    public static final String kCookie_indeed = "indeed";
    public static final String kCookie_familytreenow = "familytreenow";
    public static final String kCookie_veromi = "veromi";
    public static final String[] kCookies = new String[]{
            kCookie_Spokeo, kCookie_icris, kCookie_neighborwho, kCookie_indeed, kCookie_familytreenow, kCookie_veromi
    };

    //  不同搜索类型
    public static final Integer Simple_Search = 0;
    public static final Integer Single_Search = 1;
    public static final Integer Advanced_Search = 2;

    //  每天验证码最大发送次数
    public static final Integer EMAIL_CODE_COUNT = 10;

    //  验证码位数
    public static final Integer Email_Code_Num = 6;

    //  人员预览类型 0/人物 1/组织 2/其他
    public static final int VIEWTYPE_people = 0;
    public static final int VIEWTYPE_organization = 1;
    public static final int VIEWTYPE_other = 2;

    //  下载word参数数量 人物 组织 其他
    public static final int PARAMETER_NUM_people = 26;
    public static final int PARAMETER_NUM_organization = 19;
    public static final int PARAMETER_NUM_other = 3;


    //  cache
    public final static String kRedis_IndexCV = "IndexCV";
    public final static String kRedis_TodayCV = "TodayCV";
    public final static String kRedis_UserData = "UserData";
    public final static String kRedis_Message = "kMessage";

    //工单异常描述
    public final static String orderIsDelay = "工单已存在延期申请";
    public final static String orderIsNull = "工单异常，未找到该工单";
    public final static String orderNotRepeatBack = "工单不可多次退回";
    public final static String orderNotAction = "未知的工单操作";
    public final static String orderBackRemarksNull = "请填写退回工单描述";
    public final static String orderOnGoing = "请勿重复操作,工单已向下流转";
    public final static String orderOverTimeNotFound = "未查到工单类型超时时间";
    public final static String organiztionIsNull = "单位缺失，请联系开发";
    public final static String orderOrderNotFinish = "该工单尚有未完成的分派任务";
    public final static String orderOrderRepeatAssignment = "工单不可重复分派已指定部门";
    //企业异常描述
    public final static String userOgranizationIsNull = "该账户未匹配到部门";
    public final static String ogranizationNameIsRepeat = "部门下已有同名部门";

    //workOrder
    public final static String kWorkerOrderNoPerfix = "LA";
    //myservice
    public final static String kMyServiceNoPerfix = "MS";
    //myBulletin
    public final static String kMyBulletinNoPerfix = "MB";

    /**
     * 直播相关
     */
    public static final String liveStatus = "http://statcgi.video.qcloud.com/common_access?cmd=%s&interface=%s&t=%s&sign=%s";
//    public static final String liveStatus = "http://statcgi.video.qcloud.com/common_access?cmd=%s&interface=%s&t=%s&sign=%s";

    //  搜索类型,0/简单搜索,1/单资源搜索,2/高级搜索,3/代查询
    public enum SearchType {
        Simple,
        Single,
        Advanced,
        Artificial
    }

    //  角色 审核类型 0/新建,1/修改,2/发布,3/删除
    public enum kRoleApprovalType {
        New,
        Modify,
        Publish,
        Delete
    }

    //  角色 审批状态 0/审核不通过,1/草稿,2/待审核,3/审核通过
    public enum kRoleApprovalStatus {
        Reject,
        Draft,
        Pending,
        Success
    }


}
