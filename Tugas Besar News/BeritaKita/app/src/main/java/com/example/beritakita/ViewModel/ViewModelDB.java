package com.example.beritakita.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.beritakita.Model.ArticleDB;
import com.example.beritakita.Repository.ArticleRepositoryDB;

import java.util.List;

public class ViewModelDB extends AndroidViewModel {

    public ArticleRepositoryDB repositoryDB;
    public LiveData<List<ArticleDB>> getAllBookmark;

    public ViewModelDB(@NonNull Application application) {
        super(application);

        repositoryDB = new ArticleRepositoryDB(application);
        getAllBookmark = repositoryDB.getAllBookmark;
    }
    public void insertNote(ArticleDB articleDB){
        repositoryDB.insertNotes(articleDB);
    }
    public void deleteNote(ArticleDB articleDB){
        repositoryDB.deleteNotes(articleDB);
    }
}
