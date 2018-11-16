package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;

public class ShoppingListDao {

    public static ArrayList<ShoppingItem> getAllItems() {
        ArrayList<ShoppingItem> items = new ArrayList<>(ShoppingItemConstants.ICON_IDS.length);
        String name;
        int id,imageId;
        for (int i=0;i<ShoppingItemConstants.ICON_IDS.length;++i) {
            id = i;
            name = ShoppingItemConstants.ITEM_NAMES_RAW[i];
            imageId = ShoppingItemConstants.ICON_IDS[i];
            items.add(new ShoppingItem(id,name,imageId));
        }
        return items;
    }
}
