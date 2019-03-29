package com.lambdaschool.sprint2_challenge;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListRepository {

    private static ArrayList<ShoppingItem> shoppingItems = new ArrayList<>();

    public static ArrayList<ShoppingItem>  getShoppingItems() {
        for (int i = 0; i < ShoppingItemConstants.ITEM_NAMES_RAW.length - 1; i++) {
            ShoppingItem item = new ShoppingItem(
                    ShoppingItemConstants.ITEM_NAMES_RAW[i]
                    ,i , ShoppingItemConstants.ICON_IDS[i]);
            shoppingItems.add(item);
        }
        return shoppingItems;
    }

    public static void addItem(int index) {
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        String idCsv = MainActivity.preferences.getString("id list", "");
        String[] idArray = idCsv.split(",");
        ArrayList<String> idList = new ArrayList<>(idArray.length);
        idList.addAll(Arrays.asList(idArray));
        idList.add(Integer.toString(index));
        StringBuilder ids = new StringBuilder();
        for (String id : idList)
            ids.append(id).append(",");
        editor.putString("id list", ids.toString());
        editor.apply();
    }


}
