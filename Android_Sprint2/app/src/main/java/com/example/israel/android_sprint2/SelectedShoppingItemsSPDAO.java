package com.example.israel.android_sprint2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.ArrayList;
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
        // TODO should i use default sp instead?
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

    public static boolean isShoppingItemSelected(ShoppingItem shoppingItem) {
        Set<String> idSet = getSelectedShoppingItemIds();
        for (String id : idSet) {
            if (id.equals(shoppingItem.getId())) {
                return true;
            }
        }

        return false;
    }

    public static void addSelectedShoppingItem(ShoppingItem shoppingItem) {
        Set<String> idSet = sharedPreferences.getStringSet(KEY_SP_SELECTED_SHOPPING_ITEM_IDS, null);
        if (idSet == null) { // value at key is uninitialized
            idSet = new HashSet<>(); // initialize
        }

        // TODO check if id is already added to prevent duplicates

        idSet.add(shoppingItem.getId());
        // @NOTE: apparently stringSet will not save when the app is closed
        // to go around this, the set is removed first before putting in a new one on the same key
        // https://stackoverflow.com/questions/17469583/setstring-in-android-sharedpreferences-does-not-save-on-force-close
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_SP_SELECTED_SHOPPING_ITEM_IDS);
        editor.apply();
        editor.putStringSet(KEY_SP_SELECTED_SHOPPING_ITEM_IDS, idSet);
        editor.apply();
    }

    public static void removeSelectedShoppingItem(ShoppingItem shoppingItem) {
        Set<String> idSet = sharedPreferences.getStringSet(KEY_SP_SELECTED_SHOPPING_ITEM_IDS, null);
        if (idSet == null) { // nothing to remove
            return;
        }

        // find and remove
        for (String id : idSet) {
            if (id.equals(shoppingItem.getId())) {
                idSet.remove(id);
                break;
            }
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_SP_SELECTED_SHOPPING_ITEM_IDS);
        editor.apply();
        editor.putStringSet(KEY_SP_SELECTED_SHOPPING_ITEM_IDS, idSet);
        editor.apply();
    }

    public static ArrayList<ShoppingItem> getSelectedShoppingItems() {
        Set<String> selectedIdSet = getSelectedShoppingItemIds();
        ShoppingItem[] shoppingItems = ShoppingItemRepository.getShoppingItems();
        ArrayList<ShoppingItem> selectedShoppingItems = new ArrayList<>(selectedIdSet.size());
        for (String id : selectedIdSet) {
            selectedShoppingItems.add(shoppingItems[Integer.parseInt(id)]);
        }

        return selectedShoppingItems;
    }
}
