package com.lambdaschool.sprint2_challenge;

import android.graphics.Color;

import java.util.ArrayList;

public class ShoppingListModel {

    private boolean isChecked;

    public static ArrayList<ShoppingItem> getAllItems(){
        ArrayList<ShoppingItem> items = new ArrayList<>();
        String[] itemNames = ShoppingItemConstants.ITEM_NAMES_RAW;
        int[] itemImages = ShoppingItemConstants.ICON_IDS;
        for(int i = 0; i < itemNames.length; i++){
            ShoppingItem item = new ShoppingItem(itemNames[i], itemImages[i], i, false, Color.WHITE);
            items.add(item);
        }
        return items;
    }

    public static String getItemsSelectedName(){
        String itemsSelected = "";
        ArrayList<ShoppingItem> allItems = getAllItems();
        String[] ids = getSelectedItems();
        for(ShoppingItem item : allItems){
            for(int i = 0; i < ids.length; i++){
                if(Integer.toString(item.getId()).equals(ids[i])){
                    itemsSelected += item.getName() + ", ".replaceAll("_", " ");
                }
            }
        }
        return  itemsSelected;
    }

    public static boolean getIdInSelected(int id){
        ArrayList<ShoppingItem> allItems = getAllItems();
        String[] ids = getSelectedItems();
        for(ShoppingItem item : allItems){
            for(int i = 0; i < ids.length; i++){
                if(Integer.toString(item.getId()).equals(ids[i])){
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] getSelectedItems() {
        String selectedItems = SharedPrefsDao.getSelectedIdsString();
        return selectedItems.split(",");
    }

    public static String getSelectedItemsString(){
        return SharedPrefsDao.getSelectedIdsString();
    }

    public static void addToSelectedList(int id){
        SharedPrefsDao.addId(id);
    }
    public static void removeFromSelected(int id){
        SharedPrefsDao.removeFromSelected(id);
    }


    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
