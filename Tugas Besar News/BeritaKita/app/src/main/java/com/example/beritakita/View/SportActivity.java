package com.example.beritakita.View;

import androidx.annotation.NonNull;
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
import android.view.MenuItem;
import android.widget.TextView;

import com.example.beritakita.Adapter.NewsAdapter;
import com.example.beritakita.Model.Article;
import com.example.beritakita.R;
import com.example.beritakita.ViewModel.MainViewModel;

import java.util.List;

public class SportActivity extends AppCompatActivity implements NewsAdapter.onSelectData{

    TextView titleNews;
    RecyclerView rvSportNews;
    NewsAdapter newsAdapter;
    private MainViewModel mainViewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        rvSportNews = findViewById(R.id.recyclerViewAllNews);
        titleNews = findViewById(R.id.titleAllNews);
        rvSportNews.setHasFixedSize(true);
        rvSportNews.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        progressDialog.show();
        titleNews.setText("Sports");

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getCategorySportsNews().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> newsArtikel) {
                setRecyclerView(newsArtikel);
                progressDialog.dismiss();
            }
        });
    }

    private void setRecyclerView(List<Article> newsArtikel ) {
        newsAdapter = new NewsAdapter(SportActivity.this, newsArtikel, this);
        rvSportNews.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvSportNews.setAdapter(newsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelected(Article article) {
        startActivity(new Intent(SportActivity.this, DetailActivity.class).putExtra("url", article.getUrl()));
    }
}