package com.sub.videopj;


import android.os.Bundle;
import android.view.View;

import com.age.mac.baselibrary.base.BaseActivity;
import com.age.mac.baselibrary.view.BaseNavigation;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hg.uselibrary.ARouterConfig;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouterConfig.Video_PlayList_Activity).navigation();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initNavigationBar(BaseNavigation baseNavigation) {

    }
}
