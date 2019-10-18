/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hg.uselibrary.network.gson;


import android.content.Intent;
import android.text.TextUtils;

import com.age.mac.baselibrary.base.CommonEvent;
import com.age.mac.baselibrary.base.Constants;
import com.age.mac.baselibrary.bean.BaseArrayBean;
import com.age.mac.baselibrary.bean.BaseObjectBean;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hg.uselibrary.ARouterConfig;
import com.example.hg.uselibrary.CommonConfig;
import com.example.hg.uselibrary.ModuleRequestConfig;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
  private final Gson gson;
  private final TypeAdapter<T> adapter;
  GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
    this.gson = gson;
    this.adapter = adapter;
  }

  @Override public T convert(ResponseBody value) throws IOException {
    JsonReader jsonReader = gson.newJsonReader(value.charStream());
    try {
      T respData = adapter.read(jsonReader);
      /**
       * 登录token过期，需要重新登录
       */
//      if(respData instanceof BaseObjectBean){
//        BaseObjectBean bean= (BaseObjectBean) respData;
//        if(TextUtils.equals(bean.getCode(), CommonConfig.NO_LOGIN_CODE)){
//          ARouter.getInstance().build(ARouterConfig.Login_loginActivity).withBoolean(Constants.FINISH_ACT,true).navigation();
//        }
//      }
//      if(respData instanceof BaseArrayBean){
//        BaseArrayBean bean= (BaseArrayBean) respData;
//        if(TextUtils.equals(bean.getCode(),CommonConfig.NO_LOGIN_CODE)){
//          ARouter.getInstance().build(ARouterConfig.Login_loginActivity).withBoolean(Constants.FINISH_ACT,true).navigation();
//        }
//      }

      return respData;
    }finally {
      value.close();
    }
  }
}
