package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ShoppingItem {

    private String name;
    private int image;
    private int id;
    private Boolean isSelected;

    public ShoppingItem(String name, int image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.isSelected = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
