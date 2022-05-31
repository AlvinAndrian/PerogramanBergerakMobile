package com.example.beritakita.Repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.beritakita.Response.ArticleResponse;
import com.example.beritakita.Model.Article;
import com.example.beritakita.Network.RestApiService;
import com.example.beritakita.Network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    private ArrayList<Article> users = new ArrayList<>();
    private MutableLiveData<List<Article>> mutableLiveData = new MutableLiveData<>();
    private Application application;
    public ArticleRepository(Application application) {
        this.application = application;
    }
    public MutableLiveData<List<Article>> getHeadlineList() {
        RestApiService apiService = RetrofitInstance.getService();
        Call<ArticleResponse> call = apiService.getArticleList();
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse userWrapper = response.body();
                if (userWrapper != null && userWrapper.getArticles() != null) {
                    users = (ArrayList<Article>) userWrapper.getArticles();
                    mutableLiveData.setValue(users);
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Article>> getArticleList() {
        RestApiService apiService = RetrofitInstance.getService();
        Call<ArticleResponse> call = apiService.getTodayNewsList();
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse userWrapper = response.body();
                if (userWrapper != null && userWrapper.getArticles() != null) {
                    users = (ArrayList<Article>) userWrapper.getArticles();
                    mutableLiveData.setValue(users);
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Article>> getSeeAllList() {
        RestApiService apiService = RetrofitInstance.getService();
        Call<ArticleResponse> call = apiService.getSeeAllList();
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse userWrapper = response.body();
                if (userWrapper != null && userWrapper.getArticles() != null) {
                    users = (ArrayList<Article>) userWrapper.getArticles();
                    mutableLiveData.setValue(users);
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Article>> getCategorySportsNews() {
        RestApiService apiService = RetrofitInstance.getService();
        Call<ArticleResponse> call = apiService.getCategorySportsNews();
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse userWrapper = response.body();
                if (userWrapper != null && userWrapper.getArticles() != null) {
                    users = (ArrayList<Article>) userWrapper.getArticles();
                    mutableLiveData.setValue(users);
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Article>> getCategoryBusinessNews() {
        RestApiService apiService = RetrofitInstance.getService();
        Call<ArticleResponse> call = apiService.getCategoryBusinessNews();
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse userWrapper = response.body();
                if (userWrapper != null && userWrapper.getArticles() != null) {
                    users = (ArrayList<Article>) userWrapper.getArticles();
                    mutableLiveData.setValue(users);
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Article>> getCategoryHealthNews() {
        RestApiService apiService = RetrofitInstance.getService();
        Call<ArticleResponse> call = apiService.getCategoryHealthNews();
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse userWrapper = response.body();
                if (userWrapper != null && userWrapper.getArticles() != null) {
                    users = (ArrayList<Article>) userWrapper.getArticles();
                    mutableLiveData.setValue(users);
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Article>> getCategoryScienceNews() {
        RestApiService apiService = RetrofitInstance.getService();
        Call<ArticleResponse> call = apiService.getCategoryScienceNews();
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse userWrapper = response.body();
                if (userWrapper != null && userWrapper.getArticles() != null) {
                    users = (ArrayList<Article>) userWrapper.getArticles();
                    mutableLiveData.setValue(users);
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData<List<Article>> searchNews(String query) {
        RestApiService apiService = RetrofitInstance.getService();
        Call<ArticleResponse> call = apiService.searchNews(query,RestApiService.API_KEY);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                ArticleResponse userWrapper = response.body();
                if (userWrapper != null && userWrapper.getArticles() != null) {
                    users = (ArrayList<Article>) userWrapper.getArticles();
                    mutableLiveData.setValue(users);
                }
            }
            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {

                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

}
