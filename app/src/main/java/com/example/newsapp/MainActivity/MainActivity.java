package com.example.newsapp.MainActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsapp.Fragments.News.NewsFragment;
import com.example.newsapp.Fragments.News.SharedViewModel;
import com.example.newsapp.Fragments.Tabs.TabsFragment;
import com.example.newsapp.Fragments.Tabs.TabsViewModel;
import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  {
    private ActivityMainBinding binding;

    private SharedViewModel sharedViewModel;
    private NewsFragment everythingNewsFragment;
    private TabsFragment tabsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        everythingNewsFragment=new NewsFragment();
        tabsFragment=new TabsFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean("everything",true);
        everythingNewsFragment.setArguments(bundle);
        replaceFrame(tabsFragment);
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
                    replaceFrame(everythingNewsFragment);
                    searchView.clearFocus();
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
        if (backCount>0) {
            backCount = backCount - 1;
            FragmentManager.BackStackEntry entry=getSupportFragmentManager().getBackStackEntryAt(backCount);
            if (entry.getName().contains("TabsFragment"))
                finish();
            else getSupportFragmentManager().popBackStack();
        }
        else super.onBackPressed();
    }
    private void replaceFrame(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.mainFrame.getId(),fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();

    }
}