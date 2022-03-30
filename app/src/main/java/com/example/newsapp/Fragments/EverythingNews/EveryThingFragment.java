package com.example.newsapp.Fragments.EverythingNews;

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

import com.example.newsapp.Fragments.News.NewsAdapter;
import com.example.newsapp.R;
import com.example.newsapp.databinding.FragmentEveryThingBinding;

public class EveryThingFragment extends Fragment {
    private FragmentEveryThingBinding binding;
    private SharedViewModel viewModel;
    private RecyclerView everythingRecyclerView;
    private NewsAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_every_thing, container, false);
        everythingRecyclerView = binding.EverythingRecyclerView;
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter = new NewsAdapter(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        everythingRecyclerView.setLayoutManager(layoutManager);
        everythingRecyclerView.setHasFixedSize(true);
        everythingRecyclerView.setAdapter(adapter);
        viewModel.getEverythingResponseLiveData().observe
                (getViewLifecycleOwner(),
                categoryResponse -> {
                    if (categoryResponse != null) {
                        adapter.setResponse(categoryResponse.getArticles());
                    }
                });
    }
}