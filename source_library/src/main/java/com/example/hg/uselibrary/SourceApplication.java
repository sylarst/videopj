package com.example.hg.uselibrary;

import com.age.mac.baselibrary.base.BaseApplication;

public class SourceApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CommonConfig.initSource(getApplicationContext());
    }
}
