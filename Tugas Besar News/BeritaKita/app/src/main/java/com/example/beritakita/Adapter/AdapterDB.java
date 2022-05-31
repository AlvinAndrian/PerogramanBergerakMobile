package com.example.beritakita.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.beritakita.Holder.NewsViewHolder;
import com.example.beritakita.Model.Article;
import com.example.beritakita.Model.ArticleDB;
import com.example.beritakita.R;
import com.example.beritakita.Time.TimeUnits;
import com.example.beritakita.View.DetailActivity;

import java.util.List;

public class AdapterDB extends RecyclerView.Adapter<NewsViewHolder> {
    private List<ArticleDB> articleListDB;
    private Context mContext;
    private NewsAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(ArticleDB articleDB);
    }

    public ArticleDB getArticleAt(int position) {
        return articleListDB.get(position);
    }

    public AdapterDB(Context context, List<ArticleDB> articleListDB, NewsAdapter.onSelectData onSelectData) {
        this.mContext = context;
        this.articleListDB = articleListDB;
        this.onSelectData = onSelectData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, viewGroup, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ArticleDB articleDB = articleListDB.get(position);
        Glide.with(mContext)
                .load(articleDB.articleUrlToImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_list)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.image);

        holder.title.setText(articleDB.articleTitle);
        holder.publishedAt.setText(TimeUnits.getTimeAgo(articleDB.articlePublishedAt));
        holder.publiser.setText(articleDB.articlePublish);
        holder.content.setText(articleDB.articleContent);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("judul",articleDB.articleTitle);
            intent.putExtra("content",articleDB.articleContent);
            intent.putExtra("url",articleDB.articleUrl);
            intent.putExtra("image",articleDB.articleUrlToImage);
            intent.putExtra("publisher",articleDB.articlePublish);
            intent.putExtra("waktu",articleDB.articlePublishedAt);
            intent.putExtra("author",articleDB.articleAuthor);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getItemCount() {
        if (articleListDB != null && articleListDB.size() > 0) {
            return articleListDB.size();
        } else {
            return 0;
        }
    }
}
