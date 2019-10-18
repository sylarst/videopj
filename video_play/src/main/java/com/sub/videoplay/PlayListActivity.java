package com.sub.videoplay;

import com.age.mac.baselibrary.base.BaseActivity;
import com.age.mac.baselibrary.view.BaseNavigation;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.hg.uselibrary.ARouterConfig;

@Route(path = ARouterConfig.Video_PlayList_Activity)
public class PlayListActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.play_list_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initNavigationBar(BaseNavigation baseNavigation) {

    }
}
