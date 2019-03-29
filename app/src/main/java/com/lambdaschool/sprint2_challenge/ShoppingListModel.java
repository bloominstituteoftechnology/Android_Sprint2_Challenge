package com.lambdaschool.sprint2_challenge;

public class ShoppingListModel {
    private int id;
    private String itemName;
    private boolean isAddedCheck;

    public ShoppingListModel(int id, String itemName, boolean isAddedCheck) {
        this.id = id;
        this.itemName = itemName;
        this.isAddedCheck = isAddedCheck;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isAddedCheck() {
        return isAddedCheck;}


    @Override
    public String toString() {
        return String.format("ID: %d Name: %s", id);
    }
}
