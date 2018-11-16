package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;

public class ShoppingListDao {

    public static ArrayList<ShoppingItem> getAllItems() {
        ArrayList<ShoppingItem> items = new ArrayList<>(ShoppingItemConstants.ICON_IDS.length);
        String name;
        int imageId, id;

        for (int i=0;i<ShoppingItemConstants.ICON_IDS.length;i++) {
            ShoppingItem item = null;
            item.id = i;
            item.name = ShoppingItemConstants.ITEM_NAMES_RAW[i];
            item.imageId = ShoppingItemConstants.ICON_IDS[i];
        }
        return items;
    }
}
