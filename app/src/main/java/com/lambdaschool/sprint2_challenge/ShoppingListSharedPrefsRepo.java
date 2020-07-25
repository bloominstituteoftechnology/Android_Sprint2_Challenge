package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

// responsible for managing shopping list long term storage
public class ShoppingListSharedPrefsRepo {
    private static final String LIST_PREFERENCES = "ShoppingListPreferences";

    private static final String ID_LIST_KEY           = "id_list";
    private static final String ENTRY_ITEM_KEY_PREFIX = "entry_";
    private static final String NEXT_ID_KEY           = "next_id";


    private SharedPreferences prefs;

    public ShoppingListSharedPrefsRepo(Context context) {
        prefs = context.getSharedPreferences(LIST_PREFERENCES, Context.MODE_PRIVATE);
    }



    private ArrayList<String> getListOfIds() {
        String            idList  = prefs.getString(ID_LIST_KEY, "");
        String[]          oldList = idList.split(",");
        ArrayList<String> ids     = new ArrayList<>(oldList.length);
        if (!idList.equals("")) {
            ids.addAll(Arrays.asList(oldList));
        }
        return ids;
    }

    // read an existing entry
    public ShoppingList readEntry(int id) {
        String entryCsv = prefs.getString(ENTRY_ITEM_KEY_PREFIX + id, "invalid");
        if (!entryCsv.equals("invalid")) {
            ShoppingList item = new ShoppingList(entryCsv);
            return item;
        } else {
            return null;
        }
    }

    // read all entries
    public ArrayList<ShoppingList> readAllItems() {
        // read list of item ids
        final ArrayList<String> listOfIds = getListOfIds();

        // step through that list and read each entry
        ArrayList<ShoppingList> shoppingList = new ArrayList<>();
        for (String id : listOfIds) {
            shoppingList.add(readEntry(Integer.parseInt(id)));
        }
        return shoppingList;
    }

    // edit an existing entry
    public void updateShoppingList(ShoppingList item) {
        final SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ENTRY_ITEM_KEY_PREFIX + item.getId(), item.toCsvString());
        editor.apply();
    }
    }
