package com.example.beritakita.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.beritakita.Adapter.HeadlineAdapter;
import com.example.beritakita.Adapter.NewsAdapter;
import com.example.beritakita.Model.Article;
import com.example.beritakita.Network.RetrofitInstance;
import com.example.beritakita.R;
import com.example.beritakita.Response.ArticleResponse;
import com.example.beritakita.ViewModel.MainViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllActivity extends AppCompatActivity implements NewsAdapter.onSelectData{
    RecyclerView recyclerViewAllNews;
    TextView titleNews;

    private MainViewModel mainViewModel;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);
        getSupportActionBar().hide();
        recyclerViewAllNews = findViewById(R.id.recyclerViewAllNews);
        titleNews = findViewById(R.id.titleAllNews);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        titleNews.setText("All News");

        getSeeAll();
    }
    public void getSeeAll() {
        mainViewModel.getSeeAll().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> SeeAllList) {
                setRecyclerView(SeeAllList);
            }
        });
    }
    private void setRecyclerView(List<Article> SeeAllList ) {
        newsAdapter = new NewsAdapter(SeeAllActivity.this, SeeAllList, this);
        recyclerViewAllNews.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewAllNews.setAdapter(newsAdapter);
    }

    @Override
    public void onSelected(Article article) {
        startActivity(new Intent(SeeAllActivity.this, DetailActivity.class).putExtra("url", article.getUrl()));
    }
}