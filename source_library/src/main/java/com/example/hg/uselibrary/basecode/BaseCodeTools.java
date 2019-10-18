package com.example.hg.uselibrary.basecode;

import java.util.List;

public class BaseCodeTools {

    private BaseCodeTools(){}
    private static BaseCodeTools instance = new BaseCodeTools();
    private List<BaseCodeBean> mBaseCodeBean;

    public static BaseCodeTools getBaseCodeTools(){
        return instance;
    }

    public void setBaseCode(List<BaseCodeBean> bean){
        mBaseCodeBean=bean;
    }

    public List<BaseCodeBean> getBaseCode(){
        return mBaseCodeBean;
    }
}
