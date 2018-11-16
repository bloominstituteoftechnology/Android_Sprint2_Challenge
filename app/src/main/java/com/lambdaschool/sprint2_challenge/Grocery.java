package com.lambdaschool.sprint2_challenge;

import java.io.Serializable;

public class Grocery implements Serializable {
    private int icon;
    private String  name;


    public Grocery(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
