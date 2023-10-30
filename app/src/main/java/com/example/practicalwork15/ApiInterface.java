package com.example.practicalwork15;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("manga")
    Call<ArrayList<Manga>> getMangaList();

    @GET("manga/{id}")
    Call<Manga> getMangaById(@Path("id") int id);

    @POST("manga/create")
    Call<Manga> createManga(@Body Manga Manga);

    @PUT("manga/update")
    Call<Manga> updateManga(@Body Manga Manga);

    @DELETE("manga/delete/{id}")
    Call<String> deleteManga(@Path("id") int id);
}
