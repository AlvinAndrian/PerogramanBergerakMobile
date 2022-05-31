package com.example.beritakita.Network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static RestApiService service;
    public static RestApiService getService() {

        if (service == null) {
            // Membuat base URL
            String BASE_URL = "https://newsapi.org/v2/";

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.client(httpClient.build()).build();

            service = retrofit.create(RestApiService.class);
        }
        return service;
    }
}
