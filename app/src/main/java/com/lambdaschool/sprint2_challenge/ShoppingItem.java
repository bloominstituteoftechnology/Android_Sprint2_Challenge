package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {

    private String itemName;
    private int itemId;
    private int itemImageId;
    private boolean isChecked;

    public ShoppingItem(String itemName, int itemImageId, int itemId) {
        this.itemName = itemName;
        this.itemImageId = itemImageId;
        this.itemId = itemId;
        this.isChecked = false;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
