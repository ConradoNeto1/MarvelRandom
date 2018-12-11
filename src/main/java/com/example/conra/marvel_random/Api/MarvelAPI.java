package com.example.conra.marvel_random.Api;

import com.example.conra.marvel_random.entity.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarvelAPI {

    @GET("characters/{characterId}")
    Call<ApiResponse> getCharacter(@Path("characterId") int characterId, @Query("apikey") String apikey, @Query("ts") String ts, @Query("hash") String hash);
}
