package com.example.beritakita.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.beritakita.Adapter.AdapterDB;
import com.example.beritakita.Adapter.NewsAdapter;
import com.example.beritakita.Model.Article;
import com.example.beritakita.Model.ArticleDB;
import com.example.beritakita.R;
import com.example.beritakita.ViewModel.ViewModelDB;

import java.util.List;

public class BookmarkActivity extends AppCompatActivity implements NewsAdapter.onSelectData {

    RecyclerView recyclerViewBookmark;
    ViewModelDB viewModelDB;
    AdapterDB adapterBookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        getSupportActionBar().hide();
        recyclerViewBookmark = findViewById(R.id.recyclerViewBookmark);
        viewModelDB = ViewModelProviders.of(this).get(ViewModelDB.class);

        viewModelDB.getAllBookmark.observe(this, new Observer<List<ArticleDB>>() {
            @Override
            public void onChanged(List<ArticleDB> articleDBS) {
                setAdapter(articleDBS);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModelDB.deleteNote(adapterBookmark.getArticleAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerViewBookmark);
    }

    public void setAdapter(List<ArticleDB> articleDBS) {
        recyclerViewBookmark.setLayoutManager(new LinearLayoutManager(this));
        adapterBookmark = new AdapterDB(BookmarkActivity.this, articleDBS, this);
        recyclerViewBookmark.setAdapter(adapterBookmark);
    }

    @Override
    public void onSelected(Article article) {
        startActivity(new Intent(BookmarkActivity.this, DetailActivity.class).putExtra("url", article.getUrl()));
    }
}