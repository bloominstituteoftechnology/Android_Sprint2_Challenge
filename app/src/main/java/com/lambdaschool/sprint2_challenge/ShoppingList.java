package com.lambdaschool.sprint2_challenge;



public class ShoppingList{
    private String grocery_name;
    private int grocery_icon;
    private boolean isChecked;


    public ShoppingList(String grocery_name, int grocery_icon){

        this.grocery_name = grocery_name;
        this.grocery_icon = grocery_icon;
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

    public String toCsvString() {
        return this.grocery_name.replaceAll(",", "");
    }
}
