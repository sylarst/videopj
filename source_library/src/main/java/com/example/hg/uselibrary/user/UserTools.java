package com.example.hg.uselibrary.user;

import com.age.mac.baselibrary.utils.SharePreferenceHelper;
import com.example.hg.uselibrary.CommonConfig;
import com.google.gson.Gson;

public class UserTools {

    private UserTools(){}
    private static UserTools instance = new UserTools();
    private SourceBean mSourceBean;

    public static UserTools getUserTools(){
        return instance;
    }

    public String getToken(){
        return mSourceBean.getToken();
    }

    public String getWarehouseId(){
        return mSourceBean.getUserInfo().getWarehouseId();
    }

    public void setWarehouseId(String warehouseId){
        mSourceBean.getUserInfo().setWarehouseId(warehouseId);
        String jsonString = new Gson().toJson(mSourceBean);
        SharePreferenceHelper.commitString(CommonConfig.USER_INFO,jsonString);
    }

    public SourceBean getUserInfo(){
        return mSourceBean;
    }

    public String getUserBean(){
        return SharePreferenceHelper.getString(CommonConfig.USER_INFO,"");
    }

    public void setToken(String token){
        mSourceBean.setToken(token);
        String jsonString = new Gson().toJson(mSourceBean);
        SharePreferenceHelper.commitString(CommonConfig.USER_INFO,jsonString);
    }

//    public void setUserInfo(UserInfoEntity userInfo){
//        mUserInfo=userInfo;
//        // 对象 -->json
//        String jsonString = new Gson().toJson(userInfo);
//        SharePreferenceHelper.commitString(CommonConfig.USER_INFO,jsonString);
//    }

    public void setUserInfo(String jsonString){
        mSourceBean = new Gson().fromJson(jsonString,SourceBean.class);
        SharePreferenceHelper.commitString(CommonConfig.USER_INFO,jsonString);
    }
}
