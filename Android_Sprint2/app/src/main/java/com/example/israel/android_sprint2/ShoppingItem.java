package com.example.israel.android_sprint2;

import android.graphics.drawable.Drawable;

public class ShoppingItem {

    public ShoppingItem(Drawable iconDrawable, String name, String id) {
        this.iconDrawable = iconDrawable;
        this.name = name;
        this.id = id;
    }

    private Drawable iconDrawable;
    private String name;
    private String id;

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
