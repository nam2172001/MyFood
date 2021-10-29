package com.food.myfood.model;

import java.io.Serializable;

public class Recipe implements Serializable {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
