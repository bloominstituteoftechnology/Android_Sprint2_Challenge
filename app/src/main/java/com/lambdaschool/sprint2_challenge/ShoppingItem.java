package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {


    public int id, imageId;
    public String name;

    public ShoppingItem(int id, String name, int imageId) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getId() {
        return id;
    }

}
