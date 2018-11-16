package com.lambdaschool.sprint2_challenge;


import android.util.Log;

import java.util.ArrayList;

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
        String ids = getSelectedItemsString();
        Log.i("selecteditems", ids);
        for(int i = 0; i < allItems.size(); i++){
            ShoppingItem item = allItems.get(i);
            String id = Integer.toString(allItems.get(i).getId());
            Log.i("allids", Integer.toString(allItems.get(i).getId()));
            if(ids.contains(id)){
                itemsSelected += item.getName() + ", ";
                Log.i("contains id", "containts id");
                Log.i("selectid", ids);
                Log.i("selectiditem", id);
            }
        }
        Log.i("addeditems", itemsSelected);
        return  itemsSelected;
    }

    public static Boolean checkIfSelected(ShoppingItem item){
        boolean selected = false;
        String[] selectedIds = getSelectedItems();
        for(String id : selectedIds){
            if(id != ""){
                Log.i("AWDAWDinput", id);
                if(item.getId() == Integer.parseInt(id)){
                    selected = true;
                    return selected;

                }
            }
        }
        return selected;
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
