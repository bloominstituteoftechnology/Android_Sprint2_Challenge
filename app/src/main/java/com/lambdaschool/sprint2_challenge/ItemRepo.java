package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;

public class ItemRepo {
    private static ArrayList<Item> items = new ArrayList<>();

    public static Item getItem(int id){
        return items.get(id);
    }

    public static void setItems(ArrayList<Item> itemsToSet){
        items = itemsToSet;
    }
}
