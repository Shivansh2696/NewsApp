package com.example.newsapp.Task;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.Model.Category.CategoryResponse;
import com.example.newsapp.Retrofit.RetrofitApi;
import com.example.newsapp.Utils;

import java.io.IOException;

public class NewsThread implements Runnable {

    private String category;
    private MutableLiveData<CategoryResponse> liveData;
    public NewsThread(String category, MutableLiveData<CategoryResponse> liveData) {
        this.category = category;
        this.liveData = liveData;
    }
    @Override
    public void run() {
        try {
            CategoryResponse categoryResponse;
            if(category.equals("Top Headline")) {
                categoryResponse = RetrofitApi.getInstance().getNewsApi().getTopHeadlinesWithCountry("in", Utils.KEY).execute().body();
            }
            else {
                categoryResponse = RetrofitApi.getInstance().getNewsApi().getCategoryResponse("in",category,Utils.KEY).execute().body();
            }
            liveData.postValue(categoryResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
