package com.lambdaschool.sprint2_challenge;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GroceryDao {

    public static ArrayList<Grocery> getGroceryInv() {
        ArrayList<Grocery> groceryInvList = new ArrayList<>();
        for (int i = 0; i < ShoppingItemConstants.ICON_IDS.length; i++) {
            groceryInvList.add(
                    new Grocery(
                            ShoppingItemConstants.ICON_IDS[i],
                            ShoppingItemConstants.ITEM_NAMES_RAW[i].replaceAll("_", " ").replaceAll("\\d", "")));
        }

        return groceryInvList;
    }
}
