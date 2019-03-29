package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class CartRepo {
    private static final String PREFERENCES = "Preferences";

    private static final String ID_LIST_KEY = "id_list";
    private static final String ENTRY_ITEM_KEY_PREFIX = "entry_";
    private static final String NEXT_ID_KEY = "next_id";

    private SharedPreferences prefs;

    public CartRepo(Context context) {
        prefs = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    public void addItemToCart(ShoppingItem shoppingItem) {//TODO: Add .contains for extra precaution
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ENTRY_ITEM_KEY_PREFIX + shoppingItem.index, shoppingItem.toCsvString());
        editor.apply();
    }

    public void removeItemFromCart(ShoppingItem shoppingItem) {//TODO: Add .contains for extra precaution
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(ENTRY_ITEM_KEY_PREFIX + shoppingItem.index);
        editor.apply();
    }

    public boolean isInCart(ShoppingItem shoppingItem) {
        return prefs.contains(ENTRY_ITEM_KEY_PREFIX + shoppingItem.index);
    }

    public String getCart(ArrayList<ShoppingItem> itemList) {
        String cartString = "";
        for (ShoppingItem shoppingItem: itemList) {
            if (prefs.contains(ENTRY_ITEM_KEY_PREFIX + shoppingItem.index)) {
                cartString.concat(shoppingItem.name + " ");
            }
        }
        return cartString;
    }
}
