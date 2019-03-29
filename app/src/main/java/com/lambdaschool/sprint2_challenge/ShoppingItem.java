package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {
    private int shoppingItemId;
    private int shoppingItemResource;
    private String shoppingItemName;
    private boolean shoppingItemInCart;

    public ShoppingItem(int shoppingItemId, int shoppingItemResource, String shoppingItemName,boolean shoppingItemInCart) {
        this.shoppingItemId = shoppingItemId;
        this.shoppingItemResource = shoppingItemResource;
        this.shoppingItemName = shoppingItemName;
        this.shoppingItemInCart=shoppingItemInCart;
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

    public boolean isShoppingItemInCart() {
        return shoppingItemInCart;
    }

    public void setShoppingItemInCart(boolean shoppingItemInCart) {
        this.shoppingItemInCart = shoppingItemInCart;
    }
}
