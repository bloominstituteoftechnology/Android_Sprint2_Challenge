package com.lambdaschool.sprint2_challenge;

public class ShoppingItem {
    private String name;
    private int image;
    private int id;

    public ShoppingItem(String name, int image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public ShoppingItem(String csvString) {
        String[] values = csvString.split(",");
        this.name = values[0];
        this.image = Integer.parseInt(values[1]);
        this.id = Integer.parseInt(values[2]);
    }

    public ShoppingItem(int id) {
        this.id = id;
    }

    public String toCsvString() {
        return this.name + "," + this.image + "," + this.id;
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

    public void setId(int id) {
        this.id = id;
    }
}
