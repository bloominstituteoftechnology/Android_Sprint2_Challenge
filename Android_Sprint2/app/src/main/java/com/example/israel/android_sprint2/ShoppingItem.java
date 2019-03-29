package com.example.israel.android_sprint2;

import android.graphics.drawable.Drawable;

public class ShoppingItem {

    public ShoppingItem(Drawable iconDrawable, String name) {
        this.iconDrawable = iconDrawable;
        this.name = name;
    }

    private Drawable iconDrawable;
    private String name;
    private boolean isSelected;

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
