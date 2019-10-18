package com.example.hg.uselibrary;

import android.content.Context;

import com.age.mac.baselibrary.base.Constants;
import com.age.mac.baselibrary.utils.SharePreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class CommonConfig {

    public static final int DEFAULT_CURRENT_PAGE = 1;
    public static String BASE_URL="http://pda.juliyou.cn/";

    public static final String REMEMBER_PASS="remeber_pass";
    public static final String REMEMBER_USER="remeber_user";
    public static final String REMEMBER_PASS_CONTENT="remeber_pass_content";
    public static final String REMEMBER_USER_CONTENT="remeber_user_content";

    public static final String USER_INFO="user_info";
    public static final String SHOW_GUIDINE="show_guide";

    public static final String SUCCESS_CODE="200";
    public static final String SUCCESS_CODE_ERROR="400";

    public static final int PAGE_SIZE = 10;

    public static final String CodeTypeId="13"; //码表
    public static final String CodeQualityId="14"; //品质码表
    public static String ORDER_XIEHUO_TYPE="1"; //卸货
    public static String ORDER_LIHUO_TYPE="2"; //理货
    public static String ORDER_SHANGJIA_TYPE="3"; //上架

    public static String NO_LOGIN_CODE="999"; //上架

    public static final String Login_Select_Bean="LoginSelectBean";
    public static final String Order_Status_Bean="OrderStatusBean";
    public static final String SPSO = "85";//移库下架
    public static final String SPPA = "86";//移库上架
    public static final String RPA = "87";//补货上架
    public static final String RSO = "88";//补货下架
    public static final String APPF = "89";//先拣后分
    public static final String MYTASK = "MyTask";

    public static void initSource(Context context){
        Constants.SP_NAME="wms";
        SharePreferenceHelper.init(context,Constants.SP_NAME);
    }
}
