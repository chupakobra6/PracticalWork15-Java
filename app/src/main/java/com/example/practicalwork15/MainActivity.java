package com.example.practicalwork15;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView mangaRecycler;
    ApiInterface apiInterface;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        setContentView(R.layout.activity_main);

        mangaRecycler = findViewById(R.id.list_manga);

        apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        Call<ArrayList<Manga>> call = apiInterface.getMangaList();

        call.enqueue(new Callback<ArrayList<Manga>>() {
            @Override
            public void onResponse(Call<ArrayList<Manga>> call, Response<ArrayList<Manga>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Manga> mangaList = response.body();
                    MangaAdapter adapter = new MangaAdapter(MainActivity.this, mangaList);

                    mangaRecycler.setHasFixedSize(true);
                    mangaRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mangaRecycler.setAdapter(adapter);
                }
                else {
                    Toast.makeText(MainActivity.this, "Ошибка со стороны клиента!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Manga>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddMangaActivity.class)));
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}