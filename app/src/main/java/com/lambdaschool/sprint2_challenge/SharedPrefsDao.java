package com.lambdaschool.sprint2_challenge;

import android.content.SharedPreferences;

public class SharedPrefsDao {
    private static final String GROCERY_IDS       = "grocery_ids";

//    public static String getIdsString() {
//        String groceryIds = "";
//        if (MainActivity.preferences != null) {
//            groceryIds = MainActivity.preferences.getString(GROCERY_IDS, "");
//        }
//        return groceryIds;
//    }
//
//    public static void addId(int id) {
//        String idsString = Integer.toString(id);
//        String selectedIds = getIdsString();
//        selectedIds = selectedIds + "," + idsString;
//        SharedPreferences.Editor editor = MainActivity.preferences.edit();
//        editor.putString(GROCERY_IDS, selectedIds.replace(" ", ""));
//        editor.apply();
//    }
//
//    public static void removeId(int id) {
//        String idsString = Integer.toString(id);
//        String selectedIds = getIdsString();
//        selectedIds = selectedIds.replace("," + idsString, "");
//        SharedPreferences.Editor editor = MainActivity.preferences.edit();
//        editor.putString(GROCERY_IDS, selectedIds.replace(" ", ""));
//        editor.apply();
//    }


}
