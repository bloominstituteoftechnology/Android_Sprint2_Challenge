package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {
    private String name;
    private int image;
    private int id;

public ShoppingItem(String name, int image, int id){
    this.name = name;
    this.image = image;
    this.id = id;
}
public ShoppingItem(String csvString){
    String[] values = csvString.split(",");
    this.name = values[0];
    this.image = Integer.parseInt(values[1]);
    this.id = Integer.parseInt(values[2]);
}




}
