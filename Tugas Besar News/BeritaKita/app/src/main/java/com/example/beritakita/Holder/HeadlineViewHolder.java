package com.example.beritakita.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.beritakita.R;

public class HeadlineViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView title;
    public TextView publishedAt;
    public TextView content;
    //    public CardView cvNews;
    public TextView publiser;
    public View view;

    public HeadlineViewHolder(View view) {
        super(view);

//        cvNews = view.findViewById(R.id.cvNews);
        image = view.findViewById(R.id.imageBerita);
        title = view.findViewById(R.id.judulBerita);
        publishedAt = view.findViewById(R.id.waktuPublish);
        content = view.findViewById(R.id.contentBerita);
        publiser = view.findViewById(R.id.namaPublisher);
        this.view = view;
    }
}
