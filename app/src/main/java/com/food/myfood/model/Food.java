package com.food.myfood.model;

import java.io.Serializable;

public class Food implements Serializable {
    String image;
    String classify;
    Boolean isFavorite = false;
    String name;
    String time;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classif) {
        this.classify = classify;
    }
}