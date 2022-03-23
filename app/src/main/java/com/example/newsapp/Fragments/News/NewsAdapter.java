package com.example.newsapp.Fragments.News;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.Model.Category.ArticlesItem;
import com.example.newsapp.Activities.OnClickDetailActivity;
import com.example.newsapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
        private final Context context;
        private List<ArticlesItem> response;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_recycler_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(response.get(position));
    }

    @Override
    public int getItemCount() {
        if(response != null)
        return response.size();
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Title;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Image);
            Title = itemView.findViewById(R.id.Title);
            cardView = itemView.findViewById(R.id.CardView);
        }

        public void bind(ArticlesItem articlesItem) {
            Glide.with(context).load(articlesItem.getUrlToImage()).into(imageView);
            Title.setText(articlesItem.getTitle());
            cardView.setOnClickListener(view -> {
                Intent intent = new Intent(context, OnClickDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("NewsDetails",articlesItem);
                intent.putExtras(bundle);
                context.startActivity(intent);
            });
        }
    }

    public void setResponse(List<ArticlesItem> response) {
        this.response = response;
        notifyDataSetChanged();
    }
}
