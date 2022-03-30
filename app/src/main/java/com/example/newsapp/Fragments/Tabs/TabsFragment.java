package com.example.newsapp.Fragments.Tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.newsapp.ViewPagerAdapter;
import com.example.newsapp.databinding.FragmentTabsBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabsFragment extends Fragment {
    private FragmentTabsBinding binding;
    private ViewPagerAdapter VPAdapter;
    private TabsViewModel viewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentTabsBinding.inflate(inflater,container,false);
        viewModel=new ViewModelProvider(this).get(TabsViewModel.class);
        VPAdapter = new ViewPagerAdapter(getChildFragmentManager(),getLifecycle());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ViewPager.setAdapter(VPAdapter);

        VPAdapter.setItems(viewModel.getFragments());
        new TabLayoutMediator(binding.NewsTabs, binding.ViewPager, (tab, position) -> {
            tab.setText(VPAdapter.getFragmentTitle().get(position));
        }).attach();

    }

}
