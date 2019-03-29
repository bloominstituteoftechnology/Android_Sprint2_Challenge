package com.example.israel.android_sprint2;

/** This repository is immutable and read only*/
public class ShoppingItemRepository {

    private static final ShoppingItem[] shoppingItems = new ShoppingItem[ShoppingItemConstants.ICON_IDS.length];
    static { // initialized statically
        for (int i = 0; i < ShoppingItemConstants.ICON_IDS.length; ++i) {
            shoppingItems[i] = new ShoppingItem(
                    ShoppingItemConstants.ICON_IDS[i],
                    ShoppingItemConstants.ITEM_NAMES_RAW[i],
                    Integer.toString(i)
            );
        }
    }

    /** Do not edit the array */
    public static ShoppingItem[] getShoppingItems() {
        return shoppingItems;
    }
}
