package com.example.newsapp.Fragments.News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.databinding.NewsFragmentBinding;

public class NewsFragment extends Fragment {

    private NewsFragmentBinding binding;
    private NewsFragmentViewModel viewModel;
    private RecyclerView NewsRecyclerView;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false);
        viewModel = new ViewModelProvider(this,new NewsViewModelFactory(category)).get(NewsFragmentViewModel.class);

        NewsRecyclerView = binding.NewsRecyclerView;
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        NewsRecyclerView.setLayoutManager(linearLayoutManager);
        NewsRecyclerView.setHasFixedSize(true);

        newsAdapter = new NewsAdapter(getContext());
        NewsRecyclerView.setAdapter(newsAdapter);

        viewModel.getCategoryResponseLiveData().observe(getViewLifecycleOwner(), categoryResponse -> {
            if (categoryResponse!=null)
                newsAdapter.setResponse(categoryResponse.getArticles());
        });

        return binding.getRoot();
    }

    public void setCategory(String category) {
        this.category = category;
    }
}