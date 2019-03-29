package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {
    private String name;
    private int image;
    private int id;
    private Boolean selected;

public ShoppingItem(String name, int image, int id,Boolean selected){
    this.name = name;
    this.image = image;
    this.id = id;
    selected = false;
}
public ShoppingItem(String csvString){
    String[] values = csvString.split(",");
    this.name = values[0];
    this.image = Integer.parseInt(values[1]);
    this.id = Integer.parseInt(values[2]);
    this.selected = Boolean.getBoolean(values[3]);

}
public Boolean getSelected(){
    return selected;
}

public String toCsvString(){
    return this.name + "," + this.image + "," +this.id + "," +this.selected;
}
public String getName(){
    return name;
}
public int getImage(){
    return image;
}
public int getId(){
    return id;
}
public void setName(String name){
    this.name = name;
}
public void setImage(int image){
    this.image = image;
}
public void setId(int id){
    this.id = id;
}
}
