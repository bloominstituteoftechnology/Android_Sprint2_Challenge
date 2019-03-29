package com.example.israel.android_sprint2;

import android.graphics.drawable.Drawable;

public class ShoppingItem {

    public ShoppingItem(Drawable iconDrawable, String name, int id) {
        this.iconDrawable = iconDrawable;
        this.name = name;
        this.id = id;
    }

    private Drawable iconDrawable;
    private String name;
    private int id;
    private boolean isSelected;

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
