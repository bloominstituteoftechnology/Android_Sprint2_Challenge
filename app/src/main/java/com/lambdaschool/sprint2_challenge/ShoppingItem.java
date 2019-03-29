package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {
    private int shoppingItemId;
    private int shoppingItemResource;
    private String shoppingItemName;

    public ShoppingItem(int shoppingItemId, int shoppingItemResource, String shoppingItemName) {
        this.shoppingItemId = shoppingItemId;
        this.shoppingItemResource = shoppingItemResource;
        this.shoppingItemName = shoppingItemName;
    }

    public int getShoppingItemId() {
        return shoppingItemId;
    }

    public int getShoppingItemResource() {
        return shoppingItemResource;
    }

    public String getShoppingItemName() {
        return shoppingItemName;
    }
}
