package com.example.newsapp.Fragments.EverythingNews;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.Fragments.News.NewsAdapter;
import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentEveryThingBinding;

import retrofit2.http.Query;

public class EveryThingFragment extends Fragment {
    FragmentEveryThingBinding binding;
    private EverythingViewModel viewModel;
    private RecyclerView everythingRecyclerView;
    NewsAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(EverythingViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_every_thing, container, false);

        String Query = getArguments().getString("query",null);

        everythingRecyclerView = binding.EverythingRecyclerView;
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        everythingRecyclerView.setLayoutManager(layoutManager);
        everythingRecyclerView.setHasFixedSize(true);

        adapter = new NewsAdapter(getContext());
        everythingRecyclerView.setAdapter(adapter);

        viewModel.getEverythingResponseLiveData().observe(getViewLifecycleOwner(),categoryResponse -> {
            adapter.setResponse(categoryResponse.getArticles());
        });

        viewModel.setQuery(Query);
        return binding.getRoot();
    }
}