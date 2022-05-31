package com.example.beritakita.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.beritakita.Dao.ArticleDao;
import com.example.beritakita.Model.ArticleDB;

@Database(entities = {ArticleDB.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();

    public static ArticleDatabase INSTANCE;

    public static ArticleDatabase getDatabaseInstance(Context context) {

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ArticleDatabase.class,
                    "Article_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
