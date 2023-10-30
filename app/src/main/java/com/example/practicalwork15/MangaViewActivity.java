package com.example.practicalwork15;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangaViewActivity extends AppCompatActivity {
    private ImageView imageMangaImage;
    private TextView textMangaName;
    private TextView textMangaDescription;
    private TextView textStudentInfo;
    private RatingBar textMangaScore;

    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        setContentView(R.layout.activity_manga_view);

        deleteButton = findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(view -> {
            int mangaId = getIntent().getIntExtra("manga_id", -1); // Получите ID манги из Intent

            if (mangaId != -1) {
                ApiInterface apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
                Call<String> call = apiInterface.deleteManga(mangaId);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MangaViewActivity.this, "Манга успешно удалена", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MangaViewActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MangaViewActivity.this, "Ошибка со стороны клиента!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MangaViewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        imageMangaImage = findViewById(R.id.mangaImage);
        textMangaName = findViewById(R.id.mangaName);
        textMangaDescription = findViewById(R.id.mangaDetails);
        textStudentInfo = findViewById(R.id.studentInfo);
        textMangaScore = findViewById(R.id.mangaScore);

        Intent intent = getIntent();
        if (intent != null) {
            String mangaImage = intent.getStringExtra("manga_image");
            String mangaName = intent.getStringExtra("manga_name");
            String mangaDescription = intent.getStringExtra("manga_description");
            String studentInfo = intent.getStringExtra("student_info");
            float mangaScore = intent.getFloatExtra("manga_score", 0.0f);

            Picasso.get().load(mangaImage).into(imageMangaImage);

            textMangaName.setText(mangaName);
            textMangaDescription.setText(mangaDescription);
            textStudentInfo.setText(studentInfo);

            textMangaScore.setRating(mangaScore);
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}