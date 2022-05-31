package com.example.beritakita.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.beritakita.Holder.HeadlineViewHolder;
import com.example.beritakita.Holder.NewsViewHolder;
import com.example.beritakita.Model.Article;
import com.example.beritakita.R;
import com.example.beritakita.Time.TimeUnits;
import com.example.beritakita.View.DetailActivity;

import java.util.List;

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineViewHolder> {
    private List<Article> articleList;
    private Context mContext;
    private HeadlineAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(Article article);
    }

    public HeadlineAdapter(Context context, List<Article> articleList, HeadlineAdapter.onSelectData onSelectData) {
        this.mContext = context;
        this.articleList = articleList;
        this.onSelectData = onSelectData;
    }

    @NonNull
    @Override
    public HeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.article_item, viewGroup, false);
        return new HeadlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeadlineViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Article news = articleList.get(position);
        Glide.with(mContext)
                .load(news.getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_list)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.image);

        holder.title.setText(news.getTitle());
        holder.publishedAt.setText(TimeUnits.getTimeAgo(news.getPublishedAt()));
        holder.publiser.setText(news.getSource().getName());
        holder.content.setText(news.getContent());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("judul",news.getTitle());
            intent.putExtra("content",news.getContent());
            intent.putExtra("url",news.getUrl());
            intent.putExtra("image",news.getUrlToImage());
            intent.putExtra("publisher",news.getSource().getName());
            intent.putExtra("waktu",news.getPublishedAt());
            intent.putExtra("author",news.getAuthor());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getItemCount() {
        if (articleList != null && articleList.size() > 0) {
            return articleList.size();
        } else {
            return 0;
        }
    }
}
