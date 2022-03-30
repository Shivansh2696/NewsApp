package com.example.newsapp.Fragments.EverythingNews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.Model.Category.CategoryResponse;
import com.example.newsapp.Task.EverythingThread;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<CategoryResponse> everythingResponseLiveData;
    public SharedViewModel() {
        everythingResponseLiveData=new MutableLiveData<>();
    }

    public void setQuery(String query) {
        new Thread(new EverythingThread(query,everythingResponseLiveData)).start();
    }

    public LiveData<CategoryResponse> getEverythingResponseLiveData() {
        return everythingResponseLiveData;
    }
}
