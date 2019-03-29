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
            shoppingListItems.concat(item + ",");
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SHOPPING_LIST_KEY, shoppingListItems);
        editor.apply();
    }

    public ArrayList<String> getShoppingList(){
        String shoppingList = prefs.getString(SHOPPING_LIST_KEY, "");
        String[] retrievedList = shoppingList.split(",");
        ArrayList<String> currentList = new ArrayList<>(retrievedList.length);
        if(!retrievedList.equals("")){
            currentList.addAll(Arrays.asList(retrievedList));
        }
        return currentList;
    }
}
