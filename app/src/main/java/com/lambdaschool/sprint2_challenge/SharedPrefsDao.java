package com.lambdaschool.sprint2_challenge;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

public class SharedPrefsDao {
    private static final String SELECTED_KEY_IDS = "selected_key_ids";
    private static final String KEY_ID_PREFIX = "key_id_";

    public static String getSelectedIdsString(){
        String selectedIds = "";
        if(MainActivity.preferences != null){
            selectedIds = MainActivity.preferences.getString(SELECTED_KEY_IDS, "");
        }
        return selectedIds;
    }

    private static String[] getSelectedIds(){
        String[] ids = getSelectedIdsString().split(",");
        return ids;
    }

    public static void addId(int id){
        String stringId = Integer.toString(id);
        String selectedIdsString = getSelectedIdsString();
        selectedIdsString = selectedIdsString + "," + stringId;
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(SELECTED_KEY_IDS, selectedIdsString);
        editor.apply();
    }

    public static void removeFromSelected(int id){
        String stringId = Integer.toString(id);
        String selectedIdsString = getSelectedIdsString();
        selectedIdsString = selectedIdsString.replace("," + stringId, "");
        SharedPreferences.Editor editor = MainActivity.preferences.edit();
        editor.putString(SELECTED_KEY_IDS, selectedIdsString);
        editor.apply();
    }
}
