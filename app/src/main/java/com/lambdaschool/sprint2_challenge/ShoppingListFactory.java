package com.lambdaschool.sprint2_challenge;


import java.util.ArrayList;

public class ShoppingListFactory {
    public static ArrayList<ShoppingList> getGroceryDataList() {
        ArrayList<ShoppingList> groceryDataArrayList = new ArrayList<>();
        for(int i = 0; i < ShoppingItemConstants.ICON_IDS.length; ++i){
            groceryDataArrayList.add(new ShoppingList(ShoppingItemConstants.ITEM_NAMES_RAW[i].replaceAll("_", " "), ShoppingItemConstants.ICON_IDS[i]));
        }
        return groceryDataArrayList;
    }
}
