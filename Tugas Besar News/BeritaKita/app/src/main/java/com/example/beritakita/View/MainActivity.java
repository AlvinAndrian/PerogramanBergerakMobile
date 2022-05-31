package com.example.beritakita.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.example.beritakita.Adapter.NewsAdapter;
import com.example.beritakita.Model.Article;
import com.example.beritakita.Adapter.HeadlineAdapter;
import com.example.beritakita.Response.ArticleResponse;
import com.example.beritakita.Network.RetrofitInstance;
import com.example.beritakita.R;
import com.example.beritakita.ViewModel.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsAdapter.onSelectData, HeadlineAdapter.onSelectData {

    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    private MainViewModel mainViewModel;
    SwipeRefreshLayout swipeRefresh;

    private HeadlineAdapter adapter;
    private NewsAdapter newsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        swipeRefresh = findViewById(R.id.swiperefresh);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView2 = findViewById(R.id.recyclerView2);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getHeadline();

        swipeRefresh.setOnRefreshListener(() -> {
            getHeadline();
        });
    }

    public void getHeadline() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getHeadline().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> headlineList) {
                swipeRefresh.setRefreshing(false);
                setRecyclerView(headlineList);
            }
        });

        mainViewModel.getNewsArticle().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> newsArtikel) {
                swipeRefresh.setRefreshing(false);
                setRecyclerView(newsArtikel);
                setRecyclerView2(newsArtikel);
            }
        });
    }
    private void setRecyclerView(List<Article> headlineList ) {
        adapter = new HeadlineAdapter(MainActivity.this, headlineList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void setRecyclerView2(List<Article> newsArtikel ) {
        newsAdapter = new NewsAdapter(MainActivity.this, newsArtikel, this);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView2.setAdapter(newsAdapter);
    }

    public void SeeAllClick (View view) {
        startActivity(new Intent(MainActivity.this, SeeAllActivity.class));
    }

    public void SportCategorie (View view) {
        startActivity(new Intent(MainActivity.this, SportActivity.class));
    }

    public void HealthCategorie (View view) {
        startActivity(new Intent(MainActivity.this, HealthActivity.class));
    }

    public void BussinessCategorie (View view) {
        startActivity(new Intent(MainActivity.this, BussinessActivity.class));
    }

    public void ScienceCategorie (View view) {
        startActivity(new Intent(MainActivity.this, ScienceActivity.class));
    }

    public void searchOnClick (View view) {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }

    public void bookmarkOnClick (View view) {
        startActivity(new Intent(MainActivity.this, BookmarkActivity.class));
    }

    @Override
    public void onSelected(Article article) {
        startActivity(new Intent(MainActivity.this, DetailActivity.class).putExtra("url", article.getUrl()));
    }
}