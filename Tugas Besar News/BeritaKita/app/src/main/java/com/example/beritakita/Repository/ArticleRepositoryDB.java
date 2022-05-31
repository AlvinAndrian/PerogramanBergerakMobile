package com.example.beritakita.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.beritakita.Dao.ArticleDao;
import com.example.beritakita.Database.ArticleDatabase;
import com.example.beritakita.Model.ArticleDB;

import java.util.List;

public class ArticleRepositoryDB {

    public ArticleDao articleDao;
    public LiveData<List<ArticleDB>> getAllBookmark;

    public ArticleRepositoryDB(Application application){
        ArticleDatabase database=ArticleDatabase.getDatabaseInstance(application);
        articleDao = database.articleDao();
        getAllBookmark = articleDao.getAllBookmark();
    }

    public void insertNotes(ArticleDB articleDB){
        articleDao.insertNotes(articleDB);
    }
    public void deleteNotes(ArticleDB articleDB){
        articleDao.deleteNotes(articleDB);
    }
}
