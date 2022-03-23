package com.example.newsapp.Fragments.EverythingNews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.Model.Category.CategoryResponse;
import com.example.newsapp.Task.EverythingThread;
import com.example.newsapp.Task.NewsThread;

public class EverythingViewModel extends ViewModel {
    private MutableLiveData<CategoryResponse> EverythingResponseLiveData;
    public EverythingViewModel() {
        EverythingResponseLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<CategoryResponse> getEverythingResponseLiveData() {
        return EverythingResponseLiveData;
    }

    public void setQuery(String query) {
        new Thread(new EverythingThread(query,EverythingResponseLiveData)).start();
    }
}
