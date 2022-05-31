package com.example.beritakita.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.beritakita.Model.ArticleDB;

import java.util.List;

@androidx.room.Dao
public interface ArticleDao {

    @Query("SELECT * FROM Article_Database")
    LiveData<List<ArticleDB>> getAllBookmark();

    @Insert
    void insertNotes(ArticleDB... articleDB);

    @Delete
    void deleteNotes(ArticleDB... articleDB);
}
