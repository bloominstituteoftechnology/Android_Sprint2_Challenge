package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;

public class FoodDataFactory {
    public static ArrayList<FoodData> getFoodDataList() {
        ArrayList<FoodData> foodDataArrayList = new ArrayList<>();
        for(int i = 0; i < ShoppingItemConstants.ICON_IDS.length; ++i) {
            foodDataArrayList.add(
                    new FoodData(ShoppingItemConstants.ICON_IDS[i], ShoppingItemConstants.ITEM_NAMES_RAW[i].replaceAll("_", " ")));
        }
        return foodDataArrayList;
    }
}
