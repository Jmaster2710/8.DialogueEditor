package com.example.joel.dialogueGame;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NamesApiService {

    String BASE_URL = "https://uinames.com/";
    String REGION = "United States";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/api/?region=" + REGION)
    Call<NameItem> getNames();
}
