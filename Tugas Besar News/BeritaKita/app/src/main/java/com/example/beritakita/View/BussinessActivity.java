package com.example.beritakita.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.beritakita.Adapter.NewsAdapter;
import com.example.beritakita.Model.Article;
import com.example.beritakita.R;
import com.example.beritakita.ViewModel.MainViewModel;

import java.util.List;

public class BussinessActivity extends AppCompatActivity implements NewsAdapter.onSelectData{

    TextView titleNews;
    RecyclerView rvBusinessNews;
    NewsAdapter newsAdapter;
    private MainViewModel mainViewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        rvBusinessNews = findViewById(R.id.recyclerViewAllNews);
        titleNews = findViewById(R.id.titleAllNews);
        rvBusinessNews.setHasFixedSize(true);
        rvBusinessNews.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        progressDialog.show();
        titleNews.setText("Bussiness");

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getCategoryBusinessNews().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> newsArtikel) {
                setRecyclerView(newsArtikel);
                progressDialog.dismiss();
            }
        });
    }

    private void setRecyclerView(List<Article> newsArtikel ) {
//        newsAdapter = new NewsAdapter(newsArtikel);
        newsAdapter = new NewsAdapter(BussinessActivity.this, newsArtikel, this);
        rvBusinessNews.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvBusinessNews.setAdapter(newsAdapter);
    }

    @Override
    public void onSelected(Article article) {
        startActivity(new Intent(BussinessActivity.this, DetailActivity.class).putExtra("url", article.getUrl()));
    }
}