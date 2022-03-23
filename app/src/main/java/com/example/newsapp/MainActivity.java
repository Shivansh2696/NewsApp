package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleObserver;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.newsapp.Fragments.EverythingNews.EveryThingFragment;
import com.example.newsapp.Fragments.News.NewsFragment;
import com.example.newsapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    ActivityMainBinding binding;
    ViewPagerAdapter VPAdapter;
    EveryThingFragment everythingfragment;
    private List<String> category = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        everythingfragment = new EveryThingFragment();
        binding.NewsTabs.setupWithViewPager(binding.ViewPager);
        category.add("Top Headline");
        category.add("Business");
        category.add("Entertainment");
        category.add("General");
        category.add("Health");
        category.add("Science");
        category.add("Sports");
        category.add("Technology");

        // ViewPager Adapter Things
        VPAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);


        for (String s : category) {
            NewsFragment fragment = new NewsFragment();
            fragment.setCategory(s);
            VPAdapter.addFragment(fragment, s);
        }
        binding.ViewPager.setAdapter(VPAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("query",query);
                    everythingfragment = new EveryThingFragment();
                    everythingfragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout,everythingfragment)
                            .addToBackStack(everythingfragment.getClass().getName()).commit();
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        int backCount = getSupportFragmentManager().getBackStackEntryCount();
        if(backCount > 0){
            getSupportFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}