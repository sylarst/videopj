package com.example.hg.uselibrary;

import com.age.mac.baselibrary.network.RetrofitClient;
import com.example.hg.uselibrary.network.gson.GsonConverterFactory;

import retrofit2.Retrofit;

public class NetWorkTools {

    public static Retrofit netWork(){
        return RetrofitClient.getInstance().getRetrofit(new AppInterceptor(),CommonConfig.BASE_URL, GsonConverterFactory.create());
    }
}
