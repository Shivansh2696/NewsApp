package com.example.newsapp.MainActivity;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.Fragments.News.NewsFragment;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private List<String> category= Arrays.asList(
            "Top Headline",
            "Business",
            "Entertainment",
            "General",
            "Health",
            "Science",
            "Sports",
            "Technology"
    );
    private LinkedHashMap<String,Fragment> fragments;

    public MainActivityViewModel() {
        fragments=new LinkedHashMap<>();
        init();
    }

    private void init(){
        for (String s : category) {
            NewsFragment fragment = new NewsFragment();
            fragment.setCategory(s);
            fragments.put(s,fragment);
        }
    }

    public List<String> getCategory() {
        return category;
    }

    public LinkedHashMap<String, Fragment> getFragments() {
        return fragments;
    }
}
