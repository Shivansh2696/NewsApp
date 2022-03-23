package com.example.newsapp.Task;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.Model.Category.CategoryResponse;
import com.example.newsapp.Retrofit.RetrofitApi;
import com.example.newsapp.Utils;

import java.io.IOException;

public class EverythingThread implements Runnable{
    private String Query;
    private MutableLiveData<CategoryResponse> liveData;
    CategoryResponse response;
    public EverythingThread(String query, MutableLiveData<CategoryResponse> liveData) {
        Query = query;
        this.liveData = liveData;
    }

    @Override
    public void run() {
        try {
            response = RetrofitApi.getInstance().getNewsApi().getEverything(Query, Utils.KEY).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        liveData.postValue(response);
    }
}
