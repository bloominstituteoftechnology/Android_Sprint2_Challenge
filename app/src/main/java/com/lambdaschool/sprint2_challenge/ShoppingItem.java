package com.lambdaschool.sprint2_challenge;

import android.net.Uri;

import java.io.Serializable;

public class ShoppingItem implements Serializable {
    public static final String TAG = "ShoppingList";
    public static final int INVALID_ID = -1;
    private String name;
    private boolean isSelected;
    private int image;
    private int id;

    public ShoppingItem(String name, int image, int id, boolean isSelected) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.isSelected = false;
    }

    public ShoppingItem(String csvString) {
        String[] values = csvString.split(",");
        // check to see if we have the right string
        if(values.length == 4) {
            // handle missing numbers or strings in the number position
            try {
                this.id = Integer.parseInt(values[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            this.name = values[1];
            this.isSelected = Boolean.parseBoolean(values[3]);
            try {
                this.image = Integer.parseInt(values[2]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            // placeholder for image will maintain csv's structure even with no provided image
            this.image = values[2].equals("unused") ? -1: Integer.parseInt(values[2]);
        }
    }

    // converting our object into a csv string that we can handle in a constructor
    String toCsvString() {
        return String.format("%d,%n,%s,%i",
                id,
                name,
                isSelected,
                image == -1 ? "unused": image);
    }

    public int getImage() { return image; }

    public void setImage(int imageInt) {
        this.image = imageInt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getSelected() { return isSelected;}

    public void setSelected(boolean isSelected) { this.isSelected = isSelected;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
