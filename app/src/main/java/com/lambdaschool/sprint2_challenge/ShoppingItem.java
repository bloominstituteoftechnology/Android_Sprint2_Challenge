package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {
    private String name;
    private int image;
    private int id;
    private boolean isSelected;
    private int color;

    public ShoppingItem(String name, int image, int id, boolean isSelected, int color) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.isSelected = isSelected;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selectecd) {
        isSelected = selectecd;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "ShoppingItem{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", id=" + id +
                '}';
    }
}
