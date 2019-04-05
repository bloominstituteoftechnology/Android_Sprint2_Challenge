package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;

public class ShoppingList {

    public static ShoppingList pop() {
        ShoppingList popList = new ShoppingList();

    }
    public ShoppingList() {
    }

    public void add(ShoppingItem item) {
        items.add(item);

    }

    public void remove(ShoppingItem item) {
        items.remove(item);
    }

    public ShoppingItem get(int id){
        return items.get(id);
    }

    public int size() { return items.size();}
    public ArrayList<ShoppingItem> items= new ArrayList<ShoppingItem>();
}
