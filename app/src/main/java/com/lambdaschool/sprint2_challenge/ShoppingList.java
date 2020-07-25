package com.lambdaschool.sprint2_challenge;

import android.content.SharedPreferences;

import java.util.ArrayList;

public class ShoppingList {

    private static ArrayList<ShoppingItem> selectedItems;

    public static ArrayList<ShoppingItem> getItemList() {
        ArrayList<ShoppingItem> itemList = new ArrayList<>(ShoppingItemConstants.ITEM_NAMES_RAW.length);
        String itemName;
        int itemId, itemImageId;
        for (int i = 0; i < ShoppingItemConstants.ITEM_NAMES_RAW.length; i++) {
            itemId = i;
            itemName = ShoppingItemConstants.ITEM_NAMES_RAW[i].replaceAll("_", " ").replaceAll("\\d", "");
            itemImageId = ShoppingItemConstants.ICON_IDS[i];
            itemList.add(new ShoppingItem(itemName, itemImageId, itemId));
        }
        return itemList;
    }

    public static ArrayList<ShoppingItem> getSelectedItems() {
        ArrayList<ShoppingItem> items = getItemList();
        selectedItems = new ArrayList<>();
        for (ShoppingItem item : items) {
            if (getCheckedStatus(item.getItemId())) {
                selectedItems.add(item);
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

    public static String selectedItemsToString(ArrayList<ShoppingItem> items) {
        String names = "Here\'s your order: ";
        for(ShoppingItem item : items) {
            names += item.getItemName() + ",";
        }
        return names;
    }
}
