package com.lambdaschool.sprint2_challenge;

import android.content.SharedPreferences;

import java.util.ArrayList;

public class ShoppingList {

    static ArrayList<ShoppingItem> selectedItems;

    public static ArrayList<ShoppingItem> getItems() {
        ArrayList<ShoppingItem> items = new ArrayList<>(ShoppingItemConstants.ITEM_NAMES_RAW.length);
        String name;
        int id, imageId;
        for (int i = 0; i < ShoppingItemConstants.ITEM_NAMES_RAW.length; i++) {
            id = i;
            name = ShoppingItemConstants.ITEM_NAMES_RAW[i];
            imageId = ShoppingItemConstants.ICON_IDS[i];
            items.add(new ShoppingItem(name, imageId, id));
        }
        return items;
    }

    public static ArrayList<ShoppingItem> getSelectedItems() {

        ArrayList<ShoppingItem> items = getItems();
        for (ShoppingItem item : items)
            if (MainActivity.preferences != null) {
                Boolean bool = getCheckedStatus(item.getId());
                if (bool) {
                    selectedItems = new ArrayList<ShoppingItem>();
                    String selectedItem = item.getName();
                    int selectedId = item.getId();
                    int selectedImage = item.getImage();
                    selectedItems.add(new ShoppingItem(selectedItem, selectedImage, selectedId));
                }
            }

        return selectedItems;
    }

    public static void setCheckedStatus(int id, boolean checked) {
        if (MainActivity.preferences != null) {
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putBoolean(Constants.KEY_ID + id, checked);
            editor.apply();
        }
    }

    public static boolean getCheckedStatus(int id) {
        boolean checked = false;
        if (MainActivity.preferences != null) {
            checked = MainActivity.preferences.getBoolean(Constants.KEY_ID + id, false);
        }
        return checked;
    }
}