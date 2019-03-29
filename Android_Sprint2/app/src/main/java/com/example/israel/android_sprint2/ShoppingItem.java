package com.example.israel.android_sprint2;

public class ShoppingItem implements Comparable<ShoppingItem> {

    public ShoppingItem(int iconId, String name, String id) {
        this.iconId = iconId;
        this.name = name;
        this.id = id;
    }

    private int iconId;
    private String name;
    private String id;

    public int getIconId() {
        return iconId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(ShoppingItem o) {
        return this.name.compareTo(o.name);
    }
}
