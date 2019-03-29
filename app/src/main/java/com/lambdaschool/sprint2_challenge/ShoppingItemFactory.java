package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;

public class ShoppingItemFactory {

    public static ArrayList<ShoppingItem> getShoppingItems() {
        ArrayList<ShoppingItem> shoppingItemArrayList = new ArrayList<>();

        for (int i = 0; i < ShoppingItemConstants.ICON_IDS.length; i++) {
            shoppingItemArrayList.add(new ShoppingItem(i, ShoppingItemConstants.ICON_IDS[i], ShoppingItemConstants.ITEM_NAMES_RAW[i].replaceAll("_", " ")));
        }

        return shoppingItemArrayList;
    }
}
