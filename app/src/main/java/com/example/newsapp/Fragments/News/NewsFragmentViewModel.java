package com.example.newsapp.Fragments.News;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.newsapp.Model.Category.CategoryResponse;
import com.example.newsapp.Task.NewsThread;


public class NewsFragmentViewModel extends ViewModel {
    private String category;

    private MutableLiveData<CategoryResponse> categoryResponseLiveData;

    public NewsFragmentViewModel(String category) {
        this.category = category;
        categoryResponseLiveData = new MutableLiveData<>();
        init();
    }

  public void init(){
         new Thread(new NewsThread(category,categoryResponseLiveData)).start();
  }

    public LiveData<CategoryResponse> getCategoryResponseLiveData() {
        return categoryResponseLiveData;
    }
}
