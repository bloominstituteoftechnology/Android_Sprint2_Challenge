package com.lambdaschool.sprint2_challenge;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Map;

public class ShoppingListDao {
    private static final String KEY_ID_PREFIX = "key_id_";

    public static ArrayList<ShoppingItem> getAllItems() {
        ArrayList<ShoppingItem> items = new ArrayList<>(ShoppingItemConstants.ICON_IDS.length);
        String name;
        int id, imageId;
        for (int i = 0; i < ShoppingItemConstants.ICON_IDS.length; ++i) {
            id = i;
            name = ShoppingItemConstants.ITEM_NAMES_RAW[i].replace("_"," ");
            name = reverseCase(name.substring(0,1)) + name.substring(1,name.length());
            imageId = ShoppingItemConstants.ICON_IDS[i];
            items.add(new ShoppingItem(id, name, imageId));
        }
        return items;
    }

    public static boolean getCheckedStatus(ShoppingItem item) {
        int status = -1;
        if (MainActivity.preferences != null) {
            status = MainActivity.preferences.getInt(item.name, -1);
        }
        boolean checked = false;
        if (status != -1) {
            checked = true;
        }
        return checked;
    }

    public static void setCheckedStatus(ShoppingItem item, boolean checked) {
        if (MainActivity.preferences != null) {
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            if (!checked) {
                editor.remove(item.name);
            } else {
                editor.putInt(item.name, item.id);
            }
            editor.apply();
        }
    }

    public static ArrayList<String> getCheckedBookNames() {
        ArrayList<String> items = new ArrayList<>();
        Map<String, ?> allEntries = MainActivity.preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            items.add(entry.getKey());
        }
        return items;
    }

    public static void resetAll() {
        if (MainActivity.preferences != null) {
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
        Map<String, ?> allEntries = MainActivity.preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            editor.remove(entry.getKey());
        }
            editor.apply();
        }
    }

    private static String reverseCase(String inputString) {
        String outputString = "";
        int unicodeInt;
        char[] charArray = inputString.toCharArray();
        for (char item:charArray) {
            unicodeInt = (int)item;
            if((unicodeInt >= 65) && (unicodeInt <= 90)) {
                unicodeInt += 32;
            } else if((unicodeInt >= 97) && (unicodeInt <= 122)) {
                unicodeInt -= 32;
            }
            outputString += Character.toString((char)unicodeInt);
        }
        return outputString;
    }

}
