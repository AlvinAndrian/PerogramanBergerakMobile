package com.example.beritakita.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.beritakita.Model.ArticleDB;
import com.example.beritakita.R;
import com.example.beritakita.ViewModel.ViewModelDB;
import com.example.beritakita.databinding.ActivityDetailBinding;

import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    String sjudul, scontent, spublisher, swaktu, sauthor, surl, simage;
    ImageView image;
    ViewModelDB viewModelDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        image = findViewById(R.id.imageDetail);
        viewModelDB = ViewModelProviders.of(this).get(ViewModelDB.class);

        sjudul = getIntent().getStringExtra("judul");
        scontent = getIntent().getStringExtra("content");
        spublisher = getIntent().getStringExtra("publisher");
        swaktu = getIntent().getStringExtra("waktu");
        sauthor = getIntent().getStringExtra("author");
        surl = getIntent().getStringExtra("url");
        simage = getIntent().getStringExtra("image");

        binding.JudulDetail.setText(sjudul);
        binding.contentDetail.setText(scontent);
        binding.publisherDetail.setText(spublisher);
        binding.waktuPublishDetail.setText(swaktu);
        binding.authorDetail.setText(sauthor);
        binding.linkDetail.setText(surl);
        Glide.with(image).load(simage).into(image);

        binding.addBookmark.setOnClickListener(v -> {
            sjudul = binding.JudulDetail.getText().toString();
            scontent = binding.contentDetail.getText().toString();
            spublisher = binding.publisherDetail.getText().toString();
            swaktu = binding.waktuPublishDetail.getText().toString();
            sauthor = binding.authorDetail.getText().toString();
            surl = binding.linkDetail.getText().toString();

            CreateNotes(sjudul, scontent, spublisher, swaktu, sauthor, surl, simage);
        });
    }

    public void CreateNotes(String sjudul, String scontent, String spublisher, String swaktu, String sauthor, String surl, String simage) {

        ArticleDB articleDB = new ArticleDB();
        articleDB.articleTitle = sjudul;
        articleDB.articleContent = scontent;
        articleDB.articleAuthor = sauthor;
        articleDB.articleUrlToImage = simage;
        articleDB.articleUrl = surl;
        articleDB.articlePublish = spublisher;
        articleDB.articlePublishedAt = swaktu;

        viewModelDB.insertNote(articleDB);
        Toast.makeText(this,"Add Bookmark Success", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void webViewOnClick (View view) {
        Intent intent = new Intent(DetailActivity.this,WebActivity.class);
        intent.putExtra("url",surl);
        startActivity(intent);
    }
}