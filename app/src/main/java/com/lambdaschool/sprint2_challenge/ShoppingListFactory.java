package com.lambdaschool.sprint2_challenge;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoppingListFactory {


    public static ArrayList<ShoppingList> getGroceryDataList() {
        ArrayList<ShoppingList> groceries = new ArrayList<>();
        String[] groceryName = ShoppingItemConstants.ITEM_NAMES_RAW;
        int[] groceryIcon = ShoppingItemConstants.ICON_IDS;
        for (int i = 0; i < groceryIcon.length; ++i) {
            ShoppingList grocery = new ShoppingList(groceryName[i].replaceAll("_", " "), groceryIcon[i], i);
            groceries.add(grocery);
        }
        return groceries;
    }

    public static ArrayList<ShoppingList> getSelectedItems() {
        ArrayList<ShoppingList> groceries = getGroceryDataList();
        ArrayList<ShoppingList> selectedItems = new ArrayList<>();
        for (ShoppingList shoppingList : groceries) {
            if (shoppingList.isChecked()) {
                selectedItems.add(shoppingList);
            }
        }
        return selectedItems;
    }

    public static String[] getSelectedItemsArray(){
        ArrayList<ShoppingList> groceries = getGroceryDataList();
        ArrayList<ShoppingList> selectedItems = new ArrayList<>();
        for (ShoppingList grocery : groceries) {
            if (grocery.isChecked()) {
                selectedItems.add(grocery);
            }
        }
        String[] stringArray;
        stringArray = selectedItems.toArray(new String[0]);
        return stringArray;
    }
}
