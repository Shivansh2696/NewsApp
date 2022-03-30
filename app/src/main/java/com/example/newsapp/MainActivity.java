package com.example.newsapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsapp.Fragments.EverythingNews.EveryThingFragment;
import com.example.newsapp.Fragments.EverythingNews.SharedViewModel;
import com.example.newsapp.Fragments.News.NewsFragment;
import com.example.newsapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private ActivityMainBinding binding;
    private ViewPagerAdapter VPAdapter;
    private EveryThingFragment everythingfragment;
    private SharedViewModel sharedViewModel;
    private Bundle searchBundle;
    private List<String> category = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        everythingfragment = new EveryThingFragment();
        searchBundle=new Bundle();
        category.add("Top Headline");
        category.add("Business");
        category.add("Entertainment");
        category.add("General");
        category.add("Health");
        category.add("Science");
        category.add("Sports");
        category.add("Technology");

        // ViewPager Adapter Things
        VPAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());


        for (String s : category) {
            NewsFragment fragment = new NewsFragment();
            fragment.setCategory(s);
            VPAdapter.addFragment(fragment, s);
        }
        binding.ViewPager.setAdapter(VPAdapter);
        new TabLayoutMediator(binding.NewsTabs, binding.ViewPager, (tab, position) -> {
            tab.setText(VPAdapter.getFragmentTitle().get(position));
        }).attach();

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
                if(query.length() > 1) {
                    sharedViewModel.setQuery(query);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainLayout,everythingfragment)
                            .addToBackStack(everythingfragment.getClass().getName())
                            .commit();
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