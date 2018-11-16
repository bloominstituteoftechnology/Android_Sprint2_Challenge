package com.lambdaschool.sprint2_challenge;

public class FoodData {
    private int foodId;
    private String foodName;

    public FoodData(int colorId, String foodName) {
        this.foodId = foodId;
        this.foodName = foodName;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }
}
