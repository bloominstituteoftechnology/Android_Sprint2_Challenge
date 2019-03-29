package com.lambdaschool.sprint2_challenge;

public class Item {

    String name;
    int imageID;
    boolean isSelected;
    int id;

    Item(int id, String name, int iconId){
        this.id = id;
        this.name = name;
        this.imageID = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
