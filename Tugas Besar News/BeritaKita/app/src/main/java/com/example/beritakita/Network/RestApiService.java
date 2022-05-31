package com.example.beritakita.Network;

import com.example.beritakita.Response.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiService {
    public static final String API_KEY = "82d21d84f1ae44f0887cce0bcba6d0cc";

    @GET("top-headlines?country=id&sortBy=popularity&apiKey="+API_KEY)
    Call<ArticleResponse> getArticleList();
//24d96934ba3149d1add3558c6402652d
    @GET("top-headlines?page=1&pageSize=5&country=id&sortBy=publishedAt&apiKey="+API_KEY)
    Call<ArticleResponse> getTodayNewsList();

    @GET("top-headlines?country=id&sortBy=publishedAt&apiKey="+API_KEY)
    Call<ArticleResponse> getSeeAllList();

    @GET("top-headlines?country=id&category=sports&apiKey="+API_KEY)
    Call<ArticleResponse> getCategorySportsNews();

    @GET("top-headlines?country=id&category=business&apiKey="+API_KEY)
    Call<ArticleResponse> getCategoryBusinessNews();

    @GET("top-headlines?country=id&category=health&apiKey="+API_KEY)
    Call<ArticleResponse> getCategoryHealthNews();

    @GET("top-headlines?country=id&category=science&apiKey="+API_KEY)
    Call<ArticleResponse> getCategoryScienceNews();

    @GET("everything")
    Call<ArticleResponse> searchNews(
            @Query("q") String query,
            @Query("apiKey") String API_KEY
    );
}
