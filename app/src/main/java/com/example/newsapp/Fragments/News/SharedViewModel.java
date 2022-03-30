package com.example.newsapp.Fragments.News;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.Model.Category.CategoryResponse;
import com.example.newsapp.Task.NewsThread;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<CategoryResponse> everythingResponseLiveData;
    public SharedViewModel() {
        everythingResponseLiveData=new MutableLiveData<>();
    }

    public void setQuery(String query) {
        new Thread(
                new NewsThread(query,everythingResponseLiveData)
                .setEverything(true))
                .start();
    }

    public LiveData<CategoryResponse> getEverythingResponseLiveData() {
        return everythingResponseLiveData;
    }
}
