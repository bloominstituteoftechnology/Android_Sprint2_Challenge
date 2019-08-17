package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

class CartRepo {
    private static final String PREFERENCES = "Preferences";
    private static final String ENTRY_ITEM_KEY_PREFIX = "entry_";

    private SharedPreferences prefs;

    CartRepo(Context context) {
        prefs = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    void addItemToCart(ShoppingItem shoppingItem) { //Add item to cart
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ENTRY_ITEM_KEY_PREFIX + shoppingItem.index, shoppingItem.toCsvString());
        editor.apply();
    }

    void removeItemFromCart(ShoppingItem shoppingItem) { //Remove item from cart
        if (prefs.contains(ENTRY_ITEM_KEY_PREFIX + shoppingItem.index)) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove(ENTRY_ITEM_KEY_PREFIX + shoppingItem.index);
            editor.apply();
        }
    }

    boolean isInCart(ShoppingItem shoppingItem) { //Check if an item is in cart
        return prefs.contains(ENTRY_ITEM_KEY_PREFIX + shoppingItem.index);
    }

    String getCart(ArrayList<ShoppingItem> itemList) { // Builds String to export cart
        StringBuilder builder = new StringBuilder();
        builder.append("My Shopping List: ");
        for (ShoppingItem shoppingItem : itemList) {
            if (prefs.contains(ENTRY_ITEM_KEY_PREFIX + shoppingItem.index)) {
                builder.append((shoppingItem.name)).append(", ");
            }
        }
        builder.deleteCharAt(builder.length() - 2); //deletes last comma
        return builder.toString();
    }
}
