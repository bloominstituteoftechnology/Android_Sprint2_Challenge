package com.lambdaschool.sprint2_challenge;

public class FoodData {
    private int foodId;
    private String foodName;
    private boolean checkBox;

    public FoodData(int foodId, String foodName) {
        this.foodId = foodId;
        this.foodName = foodName;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

    @Override
    public String toString() {
        return foodName;
    }
}


