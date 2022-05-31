package com.example.beritakita.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beritakita.R;

import org.w3c.dom.Text;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView title;
    public TextView publishedAt;
    public TextView content;
//    public CardView cvNews;
    public TextView publiser;
    public View view;

    public NewsViewHolder(View view) {
        super(view);

//        cvNews = view.findViewById(R.id.cvNews);
        image = view.findViewById(R.id.imageTodayBerita);
        title = view.findViewById(R.id.judulTodayBerita);
        publishedAt = view.findViewById(R.id.waktuPublishTodayBerita);
        content = view.findViewById(R.id.contentTodayBerita);
        publiser = view.findViewById(R.id.namaPublisherTodayBerita);
        this.view = view;
    }
}
