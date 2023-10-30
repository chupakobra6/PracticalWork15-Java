package com.example.practicalwork15;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMangaActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    private EditText editMangaName;
    private EditText editMangaDescription;
    private EditText editMangaImage;
    private EditText editMangaFIO;
    private RatingBar editMangaScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        setContentView(R.layout.activity_add_manga);

        editMangaName = findViewById(R.id.editMangaName);
        editMangaDescription = findViewById(R.id.editMangaDescription);
        editMangaImage = findViewById(R.id.editMangaImage);
        editMangaFIO = findViewById(R.id.editMangaFIO);
        editMangaScore = findViewById(R.id.editMangaScore);
        Button buttonAddManga = findViewById(R.id.buttonAddManga);

        buttonAddManga.setOnClickListener(view -> addMangaToServer());
    }

    public void addMangaToServer() {
        String mangaName = editMangaName.getText().toString();
        String mangaDescription = editMangaDescription.getText().toString();
        String mangaImage = editMangaImage.getText().toString();
        String mangaFIO = editMangaFIO.getText().toString();
        int mangaScore = Math.round(editMangaScore.getRating());

        if (mangaName.isEmpty() || mangaDescription.isEmpty() || mangaImage.isEmpty() || mangaFIO.isEmpty() || mangaScore == 0) {
            Toast.makeText(AddMangaActivity.this, "Пожалуйста, заполните все поля и установите оценку", Toast.LENGTH_LONG).show();
            return;
        }

        Manga newManga = new Manga();
        newManga.setName(mangaName);
        newManga.setDetails(mangaDescription);
        newManga.setImage(mangaImage);
        newManga.setFIO(mangaFIO);
        newManga.setScore(mangaScore);

        apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        Call<Manga> call = apiInterface.createManga(newManga);

        call.enqueue(new Callback<Manga>() {
            @Override
            public void onResponse(Call<Manga> call, Response<Manga> response) {
                if (response.isSuccessful()) {
                    Manga addedManga = response.body();

                    Toast.makeText(AddMangaActivity.this, "Манга успешно добавлена", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddMangaActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddMangaActivity.this, "Ошибка со стороны клиента!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Manga> call, Throwable t) {
                Toast.makeText(AddMangaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}