package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;

public class ItemRepo {
    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Item> selectedItems = new ArrayList<>();

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static ArrayList<Item> getSelectedItems() {
        return selectedItems;
    }

    public static void setSelectedItems(ArrayList<Item> selectedItems) {
        ItemRepo.selectedItems = selectedItems;
    }

    public static Item getItem(int id){
        return items.get(id);
    }

    public static void setItems(ArrayList<Item> itemsToSet){
        items = itemsToSet;
    }
}
