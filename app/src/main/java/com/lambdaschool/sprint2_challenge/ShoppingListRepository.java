package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;

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


}
