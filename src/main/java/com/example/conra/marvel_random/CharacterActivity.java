package com.example.conra.marvel_random;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.conra.marvel_random.Api.Constantes;
import com.example.conra.marvel_random.Api.Util;
import com.example.conra.marvel_random.entity.ApiResponse;
import com.example.conra.marvel_random.entity.Data;
import com.example.conra.marvel_random.entity.Thumbnail;
import com.example.conra.marvel_random.retrofit.RetrofitConfig;

import java.text.SimpleDateFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_layout);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String ts = sdf.format(System.currentTimeMillis());

        String hashString = ts + Constantes.PRIVATE_KEY + Constantes.PUBLIC_KEY;
        String hash = Util.getMd5Hash(hashString);

        Call<ApiResponse> call = new RetrofitConfig().getCharacter().getCharacter(1009610, Constantes.PUBLIC_KEY, ts, hash);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful()){
                    Data dados = response.body().getData();
                    String nome = dados.getResults().get(0).getName();
                    Thumbnail thumbnail = dados.getResults().get(0).getThumbnail();
                    String urlThumbnail = thumbnail.getPath() + "." + thumbnail.getExtension();

                    TextView characterName = findViewById(R.id.hero_name);
                    characterName.setText(nome);

                    ImageView avatarCharacter = findViewById(R.id.hero_thumbnail);

                    Glide.with(CharacterActivity.this)
                            .load(urlThumbnail)
                            .into(avatarCharacter);

                    Log.d("DADOS", dados.toString());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("TAG", "ERRO Character call");
            }
        });

    }
}
