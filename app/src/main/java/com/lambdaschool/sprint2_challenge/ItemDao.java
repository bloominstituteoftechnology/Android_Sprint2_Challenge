package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;


public class ItemDao {
    private static ArrayList<Item> items;
    private static String[] iconNames;
    private static int[] iconIDs;

    public ItemDao(){
        items = new ArrayList<>();
        iconNames = ShoppingItemConstants.ITEM_NAMES_RAW;
        iconIDs = ShoppingItemConstants.ICON_IDS;

        for(int i = 0; i < iconNames.length-1; i++){
            items.add(new Item(i,iconNames[i],iconIDs[i]));
        }
        ItemRepo.setItems(items);
    }

    public Item getItem(int id){
        return ItemRepo.getItem(id);
    }

}
