package com.example.newsapp.Fragments.News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.Category.CategoryResponse;
import com.example.newsapp.R;
import com.example.newsapp.databinding.NewsFragmentBinding;

public class NewsFragment extends Fragment {

    private NewsFragmentViewModel viewModel;
    private SharedViewModel sharedViewModel;
    private RecyclerView NewsRecyclerView;
    private NewsAdapter newsAdapter;
    private String category;
    private boolean everything;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category=getArguments().getString("category");
        everything=getArguments().getBoolean("everything");
        if (!everything)
        viewModel = new ViewModelProvider(this,new NewsViewModelFactory(category)).get(NewsFragmentViewModel.class);
        else
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false);
        NewsRecyclerView = binding.NewsRecyclerView;
        newsAdapter = new NewsAdapter(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        NewsRecyclerView.setHasFixedSize(true);

        NewsRecyclerView.setAdapter(newsAdapter);

        if (!everything)
            viewModel.getCategoryResponseLiveData().observe(getViewLifecycleOwner(), this::setupNews);
        else sharedViewModel.getEverythingResponseLiveData().observe(getViewLifecycleOwner(), this::setupNews);
    }

    private void setupNews(CategoryResponse categoryResponse){
        if (categoryResponse!=null)
            newsAdapter.setResponse(categoryResponse.getArticles());
    }
}