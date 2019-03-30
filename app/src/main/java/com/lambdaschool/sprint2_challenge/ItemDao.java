package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;


public class ItemDao {
    private static ArrayList<Item> items;
    private static ArrayList<Item> selectedItems;
    private static String[] iconNames;
    private static int[] iconIDs;

    public ItemDao(){
        items = new ArrayList<>();
        selectedItems = new ArrayList<>();
        MainActivity.editor.putInt("selected_items_element_length", 3);
        MainActivity.editor.apply();
        iconNames = ShoppingItemConstants.ITEM_NAMES_RAW;
        iconIDs = ShoppingItemConstants.ICON_IDS;


        if(loadSelectedItemsFromCsv() != null){
            selectedItems = loadSelectedItemsFromCsv();
        }

        for(int i = 0; i < iconNames.length-1; i++){
            items.add(new Item(i,iconNames[i],iconIDs[i]));
        }
        ItemRepo.setItems(items);
        setChecked();

    }

    public static Item getItem(int id){
        return ItemRepo.getItem(id);
    }

    public static ArrayList<Item> getAllItems(){
        return ItemRepo.getItems();
    }

    public static ArrayList<Item> getSelectedItems(){
        return selectedItems;
    }

    public static ArrayList<Item> loadSelectedItemsFromCsv(){
        ArrayList<Item> tempItems = new ArrayList<>();

        if(MainActivity.prefs.getInt("selected_items_size", 0) == 0){return null;}
        int arrayLength = MainActivity.prefs.getInt("selected_items_size", 0);
        int arrayElementLength = MainActivity.prefs.getInt("selected_items_element_length", 0);
        String[] stringArr = MainActivity.prefs.getString("selected_items", "").split(",");


        for(int i = 0; i < arrayLength*3; i += arrayElementLength){
            Item item = new Item(Integer.parseInt(stringArr[i]), stringArr[i+1],Integer.parseInt(stringArr[i+2]));
            tempItems.add(item);
        }
        return tempItems;
    }

    public static void setChecked() {
                for (int i = 0; i < selectedItems.size(); i++) {
            for (int j = 0; j < items.size(); j++) {
                if (items.get(j).getId() == selectedItems.get(i).getId()) {
                    items.get(j).setSelected(true);
                }
            }
        }
    }

    public static void updateSelected(){
        ArrayList<Item> tempList = new ArrayList<>();
        for(int i = 0; i < items.size()-1; i++){
            if(items.get(i).isSelected){
                tempList.add(items.get(i));
            }
        }
        selectedItems = tempList;
    }


        public static void saveSelectedItems(){
        MainActivity.editor.putString("selected_items" , ItemToCsvString(selectedItems));
        MainActivity.editor.putInt("selected_items_size", selectedItems.size());
        MainActivity.editor.apply();
    }

    public static String ItemToCsvString(ArrayList<Item> items){
        String csvString = "";

        for(int i = 0; i < items.size(); i++){
            csvString += (Integer.toString(items.get(i).getId()) + ',');
            csvString += (items.get(i).getName() + ',');
            if(i == items.size()-1){
                 csvString += (Integer.toString(items.get(i).getImageID()));
            }
            else{
                csvString += (Integer.toString(items.get(i).getImageID())+ ',');
            }
        }
        return csvString;
    }

    public static String getHumanReadableStringFromItemArrList(ArrayList<Item> items){
        String readableString = "";

        for(int i = 0; i < items.size(); i++){
            readableString += items.get(i).getName() + ", ";
        }

        return readableString;
    }

}





/*
package com.lambdaschool.sprint2_challenge;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

import java.util.ArrayList;


public class ItemDao {
    private static ArrayList<Item> items;
    private static ArrayList<Item> selectedItems;
    private static String[] iconNames;
    private static int[] iconIDs;

    public ItemDao() {
        items = new ArrayList<>();
        selectedItems = new ArrayList<>();
        MainActivity.editor.putInt("selected_items_element_length", 3);
        MainActivity.editor.apply();
        iconNames = ShoppingItemConstants.ITEM_NAMES_RAW;
        iconIDs = ShoppingItemConstants.ICON_IDS;


        if (loadSelectedItemsFromCsv() != null) {
            selectedItems = loadSelectedItemsFromCsv();
        }

        for (int i = 0; i < iconNames.length - 1; i++) {
            items.add(new Item(i, iconNames[i], iconIDs[i]));
        }
        ItemRepo.setItems(items);
        setChecked();
    }

    public static Item getItem(int id) {
        return ItemRepo.getItem(id);
    }

    public static ArrayList<Item> getAllItems() {
        return ItemRepo.getItems();
    }

    public static ArrayList<Item> getSelectedItems() {
        return selectedItems;
    }

    public static ArrayList<Item> loadSelectedItemsFromCsv() {
        ArrayList<Item> tempItems = new ArrayList<>();

        if (MainActivity.prefs.getInt("selected_items_size", 0) == 0) {
            return null;
        }
        // int arrayLength = MainActivity.prefs.getInt("selected_items_size", 0);
        int arrayElementLength = MainActivity.prefs.getInt("selected_items_element_length", 0);
        String[] stringArr = MainActivity.prefs.getString("selected_items", "").split(",");


        for (int i = 0; i <= stringArr.length % arrayElementLength; i += arrayElementLength) {
            Item item = new Item(Integer.parseInt(stringArr[i]), stringArr[i + 1], Integer.parseInt(stringArr[i + 2]));
            tempItems.add(item);
        }
        return tempItems;
    }

*/
/*    public static void updateSelected(){
        selectedItems.clear();
        for(int i = 0; i < items.size()-1; i++){
            if(items.get(i).isSelected){
                selectedItems.add(items.get(i));
            }
        }
        saveSelectedItems();
    }*//*


    public static void setAllFalse() {
        for (int k = 0; k < items.size(); k++) {
            items.get(k).setSelected(false);
        }

    }
    public static void updateSelected(){
        for (int i = 0; i < items.size() - 1; i++) {
            if (items.get(i).isSelected) {
                selectedItems.add(items.get(i));
            }
        }
        setAllFalse();
        saveSelectedItems();
    }


    public static void setChecked() {
        //setAllFalse();
        for (
                int i = 0; i < selectedItems.size(); i++) {
            for (int j = 0; j < items.size(); j++) {
                if (items.get(j).getId() == selectedItems.get(i).getId()) {
                    items.get(j).setSelected(true);
                }
            }
        }
        selectedItems.clear();

    }


    public static void saveSelectedItems() {
        MainActivity.editor.putString("selected_items", ItemToCsvString(selectedItems));
        MainActivity.editor.putInt("selected_items_size", selectedItems.size());
        MainActivity.editor.apply();
    }

    public static String ItemToCsvString(ArrayList<Item> items) {
        String csvString = "";

        for (int i = 0; i < items.size() - 1; i++) {
            csvString += (Integer.toString(items.get(i).getId()) + ',');
            csvString += (items.get(i).getName() + ',');
            if (i == items.size() - 1) {
                csvString += (Integer.toString(items.get(i).getImageID()));
            } else {
                csvString += (Integer.toString(items.get(i).getImageID()) + ',');
            }
        }
        return csvString;
    }

    public static String getHumanReadableStringFromItemArrList(ArrayList<Item> items) {
        String readableString = "";

        for (int i = 0; i < items.size(); i++) {
            readableString += items.get(i).getName() + ", ";
        }

        return readableString;
    }

}
*/
