package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {

    private String itemName;
    private int itemId;
    private int itemImageId;

    public ShoppingItem(String itemName, int itemId, int itemImageId) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemImageId = itemImageId;
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

    public int getItemImageId() {
        return itemImageId;
    }

    public void setItemImageId(int itemImageId) {
        this.itemImageId = itemImageId;
    }
}
