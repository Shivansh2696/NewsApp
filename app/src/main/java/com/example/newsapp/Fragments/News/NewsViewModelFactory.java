package com.example.newsapp.Fragments.News;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NewsViewModelFactory implements ViewModelProvider.Factory {
    private String category;

    public NewsViewModelFactory(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)  new NewsFragmentViewModel(category);
    }
}
