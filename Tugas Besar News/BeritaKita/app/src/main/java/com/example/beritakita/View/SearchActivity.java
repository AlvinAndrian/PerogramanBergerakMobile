package com.example.beritakita.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.beritakita.Adapter.HeadlineAdapter;
import com.example.beritakita.Adapter.NewsAdapter;
import com.example.beritakita.Model.Article;
import com.example.beritakita.Network.RestApiService;
import com.example.beritakita.Network.RetrofitInstance;
import com.example.beritakita.R;
import com.example.beritakita.Response.ArticleResponse;
import com.example.beritakita.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements NewsAdapter.onSelectData {

    private MainViewModel mainViewModel;
    RecyclerView recyclerView;
    SearchView searchView;
    NewsAdapter adapter;
    private ArrayList<Article> users = new ArrayList<>();
    private MutableLiveData<List<Article>> mutableLiveData = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchNews);
        recyclerView = findViewById(R.id.recyclerViewSearch);
        getSupportActionBar().hide();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        setupSearchView();
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getSearch(newText);
                return false;
            }
        });
    }



    public void getSearch(String query) {
        mainViewModel.searchNews(query).observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> searchList) {;
                getSearchNews(searchList);
            }
        });
    }

    private void getSearchNews(List<Article> searchList ) {
        adapter = new NewsAdapter(SearchActivity.this, searchList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSelected(Article article) {
        startActivity(new Intent(SearchActivity.this, DetailActivity.class).putExtra("url", article.getUrl()));
    }
}