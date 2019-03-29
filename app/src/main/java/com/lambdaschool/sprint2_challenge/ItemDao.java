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
    NotificationManager notificationManager;
    Context context;

    public ItemDao(){
        items = new ArrayList<>();
        selectedItems = new ArrayList<>();
        MainActivity.editor.putInt("selected_items_element_length", 3);
        MainActivity.editor.apply();
        iconNames = ShoppingItemConstants.ITEM_NAMES_RAW;
        iconIDs = ShoppingItemConstants.ICON_IDS;


        if(loadSelectedItemsFromCsv() != null){
            selectedItems = loadSelectedItemsFromCsv();
            MainActivity.editor.putString("selected_items","");
            MainActivity.editor.putInt("selected_items_size", 0);
            MainActivity.editor.apply();
        }

        for(int i = 0; i < iconNames.length-1; i++){
            items.add(new Item(i,iconNames[i],iconIDs[i]));
        }
        ItemRepo.setItems(items);
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


        for(int i = 0; i < arrayLength; i =+ arrayElementLength){
            Item item = new Item(Integer.parseInt(stringArr[i]), stringArr[i+1],Integer.parseInt(stringArr[i+2]));
            tempItems.add(item);
        }
        return tempItems;
    }

    public static void updateSelected(){
        for(int i = 0; i < items.size()-1; i++){
            if(items.get(i).isSelected){
                selectedItems.add(items.get(i));
                items.get(i).setSelected(false);
            }
        }
    }

    public static void saveSelectedItems(){
        MainActivity.clearPrefs();
        MainActivity.editor.putString("selected_items" , ItemToCsvString(selectedItems));
        MainActivity.editor.putInt("selected_items_size", selectedItems.size());
        MainActivity.editor.apply();
    }

    public static String ItemToCsvString(ArrayList<Item> items){
        String csvString = "";

        for(int i = 0; i < items.size()-1; i++){
            csvString += (Integer.toString(items.get(i).getId()) + ',');
            csvString += (Integer.toString(items.get(i).getImageID())+ ',');
            //if(i == items.size()-1){
           //     csvString += (items.get(i).getName());
            //}
            //else{
                csvString += (items.get(i).getName() + ',');
           // }
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
