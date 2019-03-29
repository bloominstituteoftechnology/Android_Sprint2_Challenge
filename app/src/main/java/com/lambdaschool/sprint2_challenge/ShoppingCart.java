package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingCart {
    private static StringBuilder shoppingListCsv = new StringBuilder();
    private static SharedPreferences appStoredPrefs;

    public static boolean isItemInTheShoppingCart(String itemInList) {
        String sbToString = shoppingListCsv.toString();
        String[] splitCsv = sbToString.split(",");
        for (String item : splitCsv) {
            if (item.equals(itemInList)) {
                return true;
            }
        }
        return false;
    }

    public static void addItemToShoppingCart(String itemInList) {
        shoppingListCsv.append(",");
        shoppingListCsv.append(itemInList);
        formatShoppingListCsv();
    }

    public static void removeItemFromShoppingCart(String itemInList) {
        String sbToString = shoppingListCsv.toString();
        shoppingListCsv = new StringBuilder();
        String[] splitCsv = sbToString.split(",");
        for (String item : splitCsv) {
            if (!item.equals(itemInList)) {
                shoppingListCsv.append(itemInList);
                shoppingListCsv.append(",");
            }
        }
        formatShoppingListCsv();
    }

    private static void formatShoppingListCsv() {
        if (shoppingListCsv.length() > 0) {
            if (shoppingListCsv.charAt(0) == ',') {
                shoppingListCsv.deleteCharAt(0);
            }
            if (shoppingListCsv.charAt(shoppingListCsv.length() - 1) == ',') {
                shoppingListCsv.deleteCharAt(shoppingListCsv.length() - 1);
            }
        }
    }

    public static String getShoppingCart() {
        String[] shoppingItemIds = shoppingListCsv.toString().split(",");
        ArrayList<String> convertedStringArray = new ArrayList<>(Arrays.asList(shoppingItemIds));
        StringBuilder shoppingList = new StringBuilder();
        for (int i = 0; i < MainActivity.shoppingItemArrayList.size(); i++) {
            ShoppingItem itemToCheck = MainActivity.shoppingItemArrayList.get(i);
            if (convertedStringArray.contains(Integer.toString(itemToCheck.getShoppingItemId()))) {
                shoppingList.append(MainActivity.shoppingItemArrayList.get(i).getShoppingItemName());
                shoppingList.append(", ");
            }
        }

        String stringShoppingCart = shoppingList.toString();
        if (stringShoppingCart.endsWith(", ")) {
            stringShoppingCart = stringShoppingCart.substring(0, stringShoppingCart.length() - 2);
        }

        return stringShoppingCart;
    }

    public static String getSharedPreferences() {
        shoppingListCsv.append(appStoredPrefs.getString("shopping_cart", ""));
        return shoppingListCsv.toString();
    }

    public static void setSharedPreferences() {
        SharedPreferences.Editor appStoredPrefsEditor = appStoredPrefs.edit();
        appStoredPrefsEditor.putString("shopping_cart", shoppingListCsv.toString());
        appStoredPrefsEditor.apply();
    }

    public static void initializeSharedPreferences(@NonNull Context context) {
        appStoredPrefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }
}
