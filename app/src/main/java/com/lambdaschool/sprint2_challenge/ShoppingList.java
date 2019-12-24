package com.lambdaschool.sprint2_challenge;


import java.io.Serializable;

public class ShoppingList implements Serializable {
    private String grocery_name;
    private int id, grocery_icon;
    private boolean isChecked;


    public ShoppingList(String grocery_name, int grocery_icon, int id){

        this.grocery_name = grocery_name;
        this.grocery_icon = grocery_icon;
        this.id = id;
    }

    public ShoppingList(String csvString) {
        String[] values = csvString.split(",");
        this.grocery_name= values[0];

        toCsvString();
    }

    public String getGrocery_name() {
        return grocery_name;
    }

    public int getGrocery_icon() {
        return grocery_icon;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toCsvString() {
        return this.grocery_name.replaceAll(",", "");
    }
}
