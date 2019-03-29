package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListRepo {
    private SharedPreferences prefs;
    private static ArrayList<ShoppingItem> selectedItems;
    private static final String ID_LIST_KEY = "id";
    private static final String ENTRY_ITEM_KEY_PREFIX = "entry_";
    private static final String NEXT_ID_KEY = "next_id";


    public ShoppingListRepo(Context context) {
        prefs = context.getSharedPreferences(Constants.SHOPPING_LIST_PREFERENCES, Context.MODE_PRIVATE);
        //create new entry


    }
        public void createEntry(ShoppingItem entry){
        SharedPreferences.Editor editor = prefs.edit();
        // add id to list

            int nextId = prefs.getInt(NEXT_ID_KEY, 0 );
            entry.setId(nextId);
            editor.putInt(NEXT_ID_KEY, ++nextId);
            String idList = prefs.getString(ID_LIST_KEY,"");
            String[] oldList = idList.split(",");
            ArrayList<String> ids = new ArrayList<>(oldList.length);
            if(!idList.equals("")) {
                ids.addAll(Arrays.asList(oldList));
            }
            ids.add(Integer.toString(entry.getId()));
            StringBuilder newIdList = new StringBuilder();
            for(String id: ids){
                newIdList.append(id).append(",");
            }
            editor.putString(ID_LIST_KEY,newIdList.toString());
            //TODO store updated id list
            //save entry
            editor.putString(ENTRY_ITEM_KEY_PREFIX +entry.getId(), entry.toCsvString());
            editor.apply();

        }
    public ShoppingItem readEntry(int id){
        String entryCsv = prefs.getString(ENTRY_ITEM_KEY_PREFIX + id,"invalid");
        if (!entryCsv.equals("invalid")){
            ShoppingItem shoppingItem = new ShoppingItem(entryCsv);
            return shoppingItem;

        }else {
            return null;
        }
    }

        //read existing entry

        //delete an entry





    public static ArrayList<ShoppingItem> getAllItems() {
        ArrayList<ShoppingItem> items = new ArrayList<>(ShoppingItemConstants.ITEM_NAMES_RAW.length);
        String name;
        int id, imageId;
        for (int i = 0; i < ShoppingItemConstants.ITEM_NAMES_RAW.length; i++) {
            id = i;
            name = ShoppingItemConstants.ITEM_NAMES_RAW[i];
            imageId = ShoppingItemConstants.ICON_IDS[i];
            items.add(new ShoppingItem(name, imageId, id,false));
        }
        return items;
    }

    public static ArrayList<ShoppingItem> getSelectedItems() {
        ArrayList<ShoppingItem> items = getAllItems();
        selectedItems = new ArrayList<>();
        for (ShoppingItem item : items) {
            if (getCheckedStatus(item.getId())) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }


    public static void setCheckedStatus(int id, boolean checked) {
        if (MainActivity.preferences != null) {
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            editor.putBoolean(Constants.ID_LIST_KEY + id, checked);
            editor.apply();
        }
    }

    public static boolean getCheckedStatus(int id) {
        boolean checked = false;
        if (MainActivity.preferences != null) {
            checked = MainActivity.preferences.getBoolean(Constants.ID_LIST_KEY + id, false);
        }
        return checked;
    }
    public static String sendSelectedItems(){
        ArrayList<ShoppingItem> sendList = getSelectedItems();
        String selectionString = "Will you order me ";
        for(ShoppingItem item: sendList){
            selectionString = selectionString + " , " + item.getName();
        }
     return selectionString;
    }
}