package com.lambdaschool.sprint2_challenge;

import java.util.ArrayList;

import static com.lambdaschool.sprint2_challenge.ShoppingItemConstants.ITEM_NAMES_RAW;

public class ShoppingListAPI {
    private static ArrayList<ShoppingListModel> dataSet;

    public static ArrayList<ShoppingListModel> getAllItems() {
        if(dataSet == null) {
            populateShoppingListData();
        }
        return dataSet;
    }

    public static ShoppingListModel getByItemName(String itemName) {
        if(dataSet == null) {
            populateShoppingListData();
        }
        for(ShoppingListModel model: dataSet) {
            if(model.getItemName()).equals(itemName()) {
                return model;
            }
        }
        return null;
    }

    private static void populateShoppingListData() {
        dataSet = new ArrayList<>(200);

        parseRawData(dataSet, ITEM_NAMES_RAW[0]);

    }

    private static void parseRawData(ArrayList<ShoppingListModel> dataModels, String dataString) {

        final String[]   lines      = dataString.split("\n");
        final String[][] dataValues = new String[lines.length][];
        String cleanLine;
        for (int i = 0; i < lines.length; ++i) {
            cleanLine = lines[i].replace("\"", "");
            dataValues[i] = cleanLine.split(",");
            dataModels.add(new ShoppingListModel(
                    Integer.parseInt(dataValues[i][0])));
        }
    }
}
