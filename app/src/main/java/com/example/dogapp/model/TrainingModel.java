package com.example.dogapp.model;

public class TrainingModel {

    String name;
    int image;
    String description;

    public TrainingModel(String name, int image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
