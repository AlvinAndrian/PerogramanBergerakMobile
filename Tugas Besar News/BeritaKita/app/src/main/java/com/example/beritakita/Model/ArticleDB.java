package com.example.beritakita.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Article_Database")
public class ArticleDB {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "article_title")
    public String articleTitle;

    @ColumnInfo(name = "article_publishedAt")
    public String articlePublishedAt;

    @ColumnInfo(name = "article_author")
    public String articleAuthor;

    @ColumnInfo(name = "article_content")
    public String articleContent;

    @ColumnInfo(name = "article_url")
    public String articleUrl;

    @ColumnInfo(name = "article_urlToImage")
    public String articleUrlToImage;

    @ColumnInfo(name = "article_publish")
    public String articlePublish;
}
