package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ShoppingItem {

    private String name;
    private Drawable image;
    private int id;

    public ShoppingItem(String name, int image, int id) {
        this.name = name;
        this.image = MainActivity.context.getResources().getDrawable(image);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
