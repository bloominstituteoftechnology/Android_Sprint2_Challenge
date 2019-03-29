package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListRepo {

    public static final String SHOPPING_LIST_KEY = "shopping_list";
    private SharedPreferences prefs;

    public ShoppingListRepo(Context context) {
        prefs = context.getSharedPreferences(SHOPPING_LIST_KEY, Context.MODE_PRIVATE);
    }

    public void updateShoppingList(ArrayList<String> list){
        String shoppingListItems = "";
        for(String item: list){
            shoppingListItems = shoppingListItems + item + ",";
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SHOPPING_LIST_KEY, shoppingListItems);
        editor.apply();
    }

    public ArrayList<String> getShoppingList(){
        String            idList  = prefs.getString(SHOPPING_LIST_KEY, "");
        String[]          oldList = idList.split(",");
        ArrayList<String> ids     = new ArrayList<>(oldList.length);
        if (!idList.equals("")) {
            ids.addAll(Arrays.asList(oldList));
        }
        return ids;
    }

    public String toCSV(ArrayList<String> list){
        String csvString = "";
        for(String string: list){
            csvString = csvString + String.format("%s,", string);
        }
        return csvString;
    }
}
