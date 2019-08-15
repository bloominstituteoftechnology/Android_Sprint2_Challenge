package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {

    private String itemName;
    private int itemId, imageId;

    public ShoppingItem(String itemName, int itemId, int imageId) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.imageId = imageId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}