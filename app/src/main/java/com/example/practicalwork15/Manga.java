package com.example.practicalwork15;

import com.google.gson.annotations.SerializedName;

public class Manga {
    @SerializedName("id_Manga")
    private int id;
    @SerializedName("manga_Name")
    private String name;
    @SerializedName("manga_Detail")
    private String details;
    @SerializedName("manga_Img")
    private String image;
    @SerializedName("student_Fio")
    private String student;
    @SerializedName("student_Score")
    private int score;

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getImage() {
        return image;
    }

    public String getStudent() {
        return student;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setFIO(String fio) {
        this.student = fio;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }
}
