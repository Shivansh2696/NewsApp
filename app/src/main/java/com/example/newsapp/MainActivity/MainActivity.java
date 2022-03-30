package com.example.newsapp.MainActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsapp.Fragments.News.NewsFragment;
import com.example.newsapp.Fragments.News.SharedViewModel;
import com.example.newsapp.R;
import com.example.newsapp.ViewPagerAdapter;
import com.example.newsapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity  {
    private ActivityMainBinding binding;
    private ViewPagerAdapter VPAdapter;
    private SharedViewModel sharedViewModel;
    private MainActivityViewModel viewModel;
    private NewsFragment everythingNewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);
        everythingNewsFragment=new NewsFragment();
        VPAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());

        Bundle bundle=new Bundle();
        bundle.putBoolean("everything",true);
        everythingNewsFragment.setArguments(bundle);

        binding.ViewPager.setAdapter(VPAdapter);

        VPAdapter.setItems(viewModel.getFragments());
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
                            .replace(R.id.mainFrame,everythingNewsFragment)
                            .addToBackStack(everythingNewsFragment.getClass().getName())
                            .commit();
                    setPagerLayoutVisibility(false);
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
            setPagerLayoutVisibility(true);
        }
        else {
            super.onBackPressed();
        }
    }

    private void setPagerLayoutVisibility(boolean visibility){
        binding.pagerLayout.setVisibility(visibility? View.VISIBLE:View.GONE);
        binding.mainFrame.setVisibility(!visibility?View.VISIBLE:View.GONE);
    }
}