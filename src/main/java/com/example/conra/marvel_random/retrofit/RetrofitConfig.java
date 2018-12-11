package com.example.conra.marvel_random.retrofit;

import com.example.conra.marvel_random.Api.MarvelAPI;
import com.example.conra.marvel_random.entity.ApiResponse;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private static final String BASE_URL = "https://gateway.marvel.com/v1/public/";
    private Retrofit retrofit;

    public RetrofitConfig(){

        OkHttpClient client = new OkHttpClient.Builder().build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MarvelAPI getCharacter(){
        return this.retrofit.create(MarvelAPI.class);
    }
}
