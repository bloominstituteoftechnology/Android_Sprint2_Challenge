package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListRepository {
    static Context context;

    public static ArrayList<ShoppingItem>  getShoppingItems() {
        ArrayList<ShoppingItem> shoppingItems = new ArrayList<>();

        for (int i = 0; i < ShoppingItemConstants.ITEM_NAMES_RAW.length - 1; i++) {
            ShoppingItem item = new ShoppingItem(
                    ShoppingItemConstants.ITEM_NAMES_RAW[i]
                    ,i , ShoppingItemConstants.ICON_IDS[i]);
            shoppingItems.add(item);
        }
        return shoppingItems;
    }

    public static String getAllIds() {
        return MainActivity.preferences.getString("id list", "");
    }
    public static void updateItem(int index, boolean checked) {
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        String idCsv = getAllIds();
        String[] parsedIds = idCsv.split(",");
        ArrayList<String> idList = new ArrayList<>(parsedIds.length);
        if (idCsv != null)
        idList.addAll(Arrays.asList(parsedIds));
        if (checked) {
            idList.remove((Integer.toString(index)));
        } else if (!checked) {
            idList.add(Integer.toString(index));
        }
        StringBuilder ids = new StringBuilder();
        for (String id : idList)
            ids.append(id).append(",");
        editor.putString("id list", ids.toString());
        editor.apply();
    }

    public static boolean containsItem(int id) {
        String itemId = Integer.toString(id);
        String idCsv = getAllIds();
        String[] parsedIds = idCsv.split(",");
        for (int i = 0; i < parsedIds.length; i++){
            if (parsedIds[i].equals(itemId))
                return true;
        }
        return false;
    }

    public static String createShoppingList() {
        ArrayList<ShoppingItem> items = getShoppingItems();
        String idCsv = MainActivity.preferences.getString("id list", "");
        String[] parsedIds = idCsv.split(",");
        String shoppingIntro = "My shopping list has the following items: ";
        StringBuilder builder = new StringBuilder();
        if(parsedIds[0].equals("")) {
            for (int i = 1; i < parsedIds.length; i++){
                String itemName = items.get(Integer.parseInt(parsedIds[i])).getItemName();
                builder.append(itemName).append(",");
            }
        } else {
            for (String id : parsedIds) {
                String itemName = items.get(Integer.parseInt(id)).getItemName();
                builder.append(itemName).append(",");
            }
        }
        return shoppingIntro + builder.toString();
    }


}