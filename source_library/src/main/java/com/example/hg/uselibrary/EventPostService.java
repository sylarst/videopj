package com.example.hg.uselibrary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.age.mac.baselibrary.base.CommonEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 邮局中心，module见通讯的消息在这集中处理
 */
public class EventPostService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostEvent(CommonEvent event) {
        switch (event.getmWhat()){
            case ModuleRequestConfig.APPLY_REQUEST: //接收module之间的请求
                //--------这个请求要求主module处理，所以调用主module中心处理相关业务。
                CommonEvent post=new CommonEvent(ModuleRequestConfig.APPLY_MIDDLE);
                Gson gson = new Gson();
                String userJson = gson.toJson("test");
                post.setmMessage(userJson);
                EventBus.getDefault().post(post);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
