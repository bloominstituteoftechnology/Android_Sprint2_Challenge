package com.lambdaschool.sprint2_challenge;

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
        String itemsSelected = "Could you please pick these up?  ";
        ArrayList<ShoppingItem> allItems = getAllItems();
        String[] ids = getSelectedItems();
        for(ShoppingItem item : allItems){
            for(int i = 0; i < ids.length; i++){
                if(Integer.toString(item.getId()).equals(ids[i])){
                    String name = item.getName().replaceAll("_", " ");
                    itemsSelected += name + ", ";
                }
            }
        }
        if (itemsSelected.endsWith(", ")) {
            itemsSelected = itemsSelected.substring(0, itemsSelected.length() - 2);
        }
        return  itemsSelected;
    }

    public static boolean isInSelected(String id){
        String[] ids = getSelectedItems();
        for(int i = 0; i < ids.length; i++){
            if((id.equals(ids[i]))){
                return true;
            }
        }
        return false;
    }

    public static String[] getSelectedItems() {
        String selectedItems = SharedPrefsDao.getSelectedIdsString();
        return selectedItems.split(",");
    }

    public static void addToSelectedList(int id){
        SharedPrefsDao.addId(id);
    }
    public static void removeFromSelected(int id){
        SharedPrefsDao.removeFromSelected(id);
    }

}
