package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {
    String name;
    int image, index;

    public ShoppingItem(String name, int image, int index) {
        this.name = name;
        this.image = image;
        this.index = index;
    }

    public ShoppingItem(String csvString){
        String[] temp = csvString.split(",");
        this.name = temp[1];
        this.image = Integer.parseInt(temp[2]);
        this.index = Integer.parseInt(temp[3]);
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getIndex() {
        return index;
    }

    public String getImageAsString() {
        //will implement if necessary
        return null;
    }

    public String getIndexAsString() {
        //will implement if necessary
        return null;
    }
}
