package com.example.practicalwork15;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MangaAdapter extends RecyclerView.Adapter<MangaAdapter.MangaViewHolder> {

    private Context context;
    private ArrayList<Manga> mangaList;

    public MangaAdapter(Context context, ArrayList<Manga> mangaList) {
        this.context = context;
        this.mangaList = mangaList;
    }

    @NonNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.manga_item, parent, false);
        return new MangaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder holder, int position) {
        final Manga manga = mangaList.get(position);

        holder.mangaName.setText(manga.getName());
        holder.mangaDetails.setText(manga.getDetails());
        String studentInfo = manga.getStudent() + " - Оценка: " + manga.getScore();
        holder.studentInfo.setText(studentInfo);

        Picasso.get().load(manga.getImage()).into(holder.mangaImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, MangaViewActivity.class);

            intent.putExtra("manga_id", manga.getId());
            intent.putExtra("manga_name", manga.getName());
            intent.putExtra("manga_description", manga.getDetails());
            intent.putExtra("manga_image", manga.getImage());
            intent.putExtra("student_info", studentInfo);
            intent.putExtra("manga_score", manga.getScore());

            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    static class MangaViewHolder extends RecyclerView.ViewHolder {
        ImageView mangaImage;
        TextView mangaName;
        TextView mangaDetails;
        TextView studentInfo;

        public MangaViewHolder(@NonNull View itemView) {
            super(itemView);
            mangaImage = itemView.findViewById(R.id.mangaImage);
            mangaName = itemView.findViewById(R.id.mangaName);
            mangaDetails = itemView.findViewById(R.id.mangaDetails);
            studentInfo = itemView.findViewById(R.id.studentInfo);
        }
    }
}
