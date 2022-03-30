package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentArrayList;
    private List<String> fragmentTitle;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.fragmentArrayList=new ArrayList<>();
        this.fragmentTitle=new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }

    public void setItems(LinkedHashMap<String,Fragment> map){
        if (map!=null){
            for (Map.Entry<String, Fragment> entry : map.entrySet()) {
                String title=entry.getKey();
                Fragment fragment=entry.getValue();
                fragmentArrayList.add(fragment);
                fragmentTitle.add(title);
            }
        }
    }

    public List<String> getFragmentTitle() {
        return fragmentTitle;
    }
}
