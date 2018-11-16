package com.lambdaschool.sprint2_challenge;


import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListModel {

    public static ArrayList<ShoppingItem> getAllItems(){
        ArrayList<ShoppingItem> items = new ArrayList<>();
        String[] itemNames = ShoppingItemConstants.ITEM_NAMES_RAW;
        int[] itemImages = ShoppingItemConstants.ICON_IDS;
        for(int i = 0; i < itemNames.length; i++){
            ShoppingItem item = new ShoppingItem(itemNames[i], itemImages[i], i);
            items.add(item);
        }
        return items;
    }

    public static String getItemsSelectedName(){
        String itemsSelected = "";
        ArrayList<ShoppingItem> allItems = getAllItems();
        String[] ids = getSelectedItems();
        Log.i("selecteditems", Arrays.toString(ids));
        for(int i = 0; i < allItems.size(); i++){
            ShoppingItem item = allItems.get(i);
            String id = Integer.toString(allItems.get(i).getId());
            Log.i("allids", Integer.toString(allItems.get(i).getId()));
            for(int j = 0; j < ids.length; i++) {
                if(Integer.toString(item.getId()) == ids[j]){
                    itemsSelected += item.getName() + ", ";
                    Log.i("contains id", "containts id");
                    //Log.i("selectid", ids);
                    Log.i("selectiditem", id);
                }
            }
        }
        Log.i("addeditems", itemsSelected);
        return  itemsSelected;
    }

    public static String[] getSelectedItems(){
        String selectedItems = SharedPrefsDao.getSelectedIdsString();
        //Log.i("selectedItems", selectedItems);
        return selectedItems.split(",");
    }

    public static String getSelectedItemsString(){
        return SharedPrefsDao.getSelectedIdsString();
    }

    public static void addToSelectedList(int id){
        SharedPrefsDao.addId(id);
        //Log.i("selectedid", Integer.toString(id));
    }
}
