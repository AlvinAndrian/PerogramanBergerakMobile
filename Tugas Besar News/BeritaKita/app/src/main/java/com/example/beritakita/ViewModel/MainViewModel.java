package com.example.beritakita.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.beritakita.Model.Article;
import com.example.beritakita.Repository.ArticleRepository;
import com.example.beritakita.View.MainActivity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        articleRepository = new ArticleRepository(application);
    }
    public LiveData<List<Article>> getHeadline() {
        return articleRepository.getHeadlineList();
    }

    public LiveData<List<Article>> getNewsArticle() {
        return articleRepository.getArticleList();
    }

    public LiveData<List<Article>> getSeeAll() {
        return articleRepository.getSeeAllList();
    }

    public LiveData<List<Article>> getCategorySportsNews() { return articleRepository.getCategorySportsNews();}

    public LiveData<List<Article>> getCategoryBusinessNews() { return articleRepository.getCategoryBusinessNews();}

    public LiveData<List<Article>> getCategoryHealthNews() { return articleRepository.getCategoryHealthNews();}

    public LiveData<List<Article>> getCategoryScienceNews() { return articleRepository.getCategoryScienceNews();}

    public LiveData<List<Article>> searchNews(String query) { return articleRepository.searchNews(query);}
}
