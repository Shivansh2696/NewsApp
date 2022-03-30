package com.example.newsapp.MainActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsapp.Fragments.EverythingNews.EveryThingFragment;
import com.example.newsapp.Fragments.EverythingNews.SharedViewModel;
import com.example.newsapp.R;
import com.example.newsapp.ViewPagerAdapter;
import com.example.newsapp.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity  {
    private ActivityMainBinding binding;
    private ViewPagerAdapter VPAdapter;
    private EveryThingFragment everythingfragment;
    private SharedViewModel sharedViewModel;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);
        everythingfragment = new EveryThingFragment();

        VPAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
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