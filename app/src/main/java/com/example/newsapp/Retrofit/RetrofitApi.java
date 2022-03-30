package com.example.newsapp.Retrofit;

import com.example.newsapp.Utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private static RetrofitApi instance;
    private OkHttpClient.Builder httpClient;
    private final NewsApi newsApi;

    public static RetrofitApi getInstance(){
        if(instance == null)
            instance = new RetrofitApi();
        return instance;
    }
    private  RetrofitApi(){
        httpClient = new OkHttpClient.Builder();
        newsApi = create(Utils.BASE_URL,NewsApi.class);
    }

    public NewsApi getNewsApi(){
        return newsApi;
    }

    private <T> T create(String baseUrl, Class<T> cls){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        T t=retrofit.create(cls);
        return t;
    }
}
