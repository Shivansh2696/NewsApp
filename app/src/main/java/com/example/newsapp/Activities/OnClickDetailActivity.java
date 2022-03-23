package com.example.newsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.newsapp.Model.Category.ArticlesItem;
import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityOnClickDetailActitivityBinding;

public class OnClickDetailActivity extends AppCompatActivity {
    ActivityOnClickDetailActitivityBinding binding;
    ArticlesItem articlesItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_click_detail_actitivity);
        articlesItem = (ArticlesItem) getIntent().getExtras().getSerializable("NewsDetails");

        binding.PublishTime.setText(articlesItem.getPublishedAt());

        if(articlesItem.getAuthor() != null)
            binding.AuthorName.setText( articlesItem.getAuthor());
        else binding.AuthorName.setText("Sunny");

        if(articlesItem.getSource() != null)
            if(articlesItem.getSource().getName() != null)
                binding.SourceName.setText(articlesItem.getSource().getName());

        binding.URL.setText(articlesItem.getUrl());
        binding.Content.setText(articlesItem.getContent());
        binding.Title.setText(articlesItem.getTitle());
        Glide.with(this).load(articlesItem.getUrlToImage()).into(binding.NewsImage);
    }
}