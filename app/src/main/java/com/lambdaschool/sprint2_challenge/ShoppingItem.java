package com.lambdaschool.sprint2_challenge;

import java.util.Locale;

public class ShoppingItem {
    String name;
    int image, index;

    ShoppingItem(String name, int image, int index) {
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
    String toCsvString(){
        return String.format(Locale.US,"%s,%d,%d",
                this.name,
                this.image,
                this.index);
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
