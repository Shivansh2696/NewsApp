package com.example.newsapp.Fragments.Tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.Fragments.News.NewsFragment;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class TabsViewModel extends ViewModel {
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

    public TabsViewModel() {
        fragments=new LinkedHashMap<>();
        init();
    }

    private void init(){
        for (String s : category) {
            NewsFragment fragment = new NewsFragment();
            Bundle bundle=new Bundle();
            bundle.putString("category",s);
            fragment.setArguments(bundle);
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
