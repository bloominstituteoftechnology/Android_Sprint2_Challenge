package com.example.israel.android_sprint2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

public class SelectedShoppingItemsSPDAO {

    public static SharedPreferences sharedPreferences;
    public static String KEY_SP_SELECTED_SHOPPING_ITEMS = "selected_shopping_items";
    public static String KEY_SP_SELECTED_SHOPPING_ITEM_IDS = "selected_shopping_item_ids";

    /**
     * Initialize sharedPreference
     * */
    public static void init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KEY_SP_SELECTED_SHOPPING_ITEMS, Context.MODE_PRIVATE);
        }
    }

    @NonNull
    public static Set<String> getSelectedShoppingItemIds() {
        Set<String> idSet = sharedPreferences.getStringSet(KEY_SP_SELECTED_SHOPPING_ITEM_IDS, null);
        if (idSet == null) { // value at key is uninitialized
            return new HashSet<>(); // return empty array
        }

        return idSet;
    }

    public static void addSelectedShoppingItem(ShoppingItem shoppingItem) {
        Set<String> idSet = sharedPreferences.getStringSet(KEY_SP_SELECTED_SHOPPING_ITEM_IDS, null);
        if (idSet == null) { // value at key is uninitialized
            idSet = new HashSet<>(); // initialize
        }

        // TODO check if id is already added to prevent duplicates

        idSet.add(shoppingItem.getId());
        sharedPreferences.edit().putStringSet(KEY_SP_SELECTED_SHOPPING_ITEM_IDS, idSet).apply();
    }

    public static boolean isShoppingItemSelected(ShoppingItem shoppingItem) {
        Set<String> idSet = getSelectedShoppingItemIds();
        for (String id : idSet) {
            if (id.equals(shoppingItem.getId())) {
                return true;
            }
        }

        return false;
    }
}
