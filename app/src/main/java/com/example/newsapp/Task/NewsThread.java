package com.example.newsapp.Task;

import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.Model.Category.CategoryResponse;
import com.example.newsapp.Retrofit.RetrofitApi;
import com.example.newsapp.Utils.Constant;

import java.io.IOException;

import retrofit2.Response;

public class NewsThread implements Runnable {

    private String category;
    private MutableLiveData<CategoryResponse> liveData;
    private boolean everything;
    public NewsThread(String category, MutableLiveData<CategoryResponse> liveData) {
        this.category = category;
        this.liveData = liveData;
    }

    @Override
    public void run() {
        try {
            Response<CategoryResponse> categoryResponse;
            if (everything){
                categoryResponse = RetrofitApi.getInstance().getNewsApi().getEverything(category, Constant.KEY).execute();
            }else
            if(category.equals("Top Headline")) {
                categoryResponse = RetrofitApi.getInstance().getNewsApi().getTopHeadlinesWithCountry("in", Constant.KEY).execute();
            }
            else {
                categoryResponse = RetrofitApi.getInstance().getNewsApi().getCategoryResponse("in",category, Constant.KEY).execute();
            }

            if (categoryResponse.isSuccessful())
                liveData.postValue(categoryResponse.body());
            else System.out.println(categoryResponse.errorBody().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NewsThread setEverything(boolean everything) {
        this.everything = everything;
        return this;
    }
}
