package com.lambdaschool.sprint2_challenge;

import android.content.SharedPreferences;

import java.util.ArrayList;

public class ShoppingListDao {
    private static final String KEY_ID_PREFIX = "key_id_";

    public static ArrayList<ShoppingItem> getAllItems() {
        ArrayList<ShoppingItem> items = new ArrayList<>(ShoppingItemConstants.ICON_IDS.length);
        String name;
        int id, imageId;
        for (int i = 0; i < ShoppingItemConstants.ICON_IDS.length; ++i) {
            id = i;
            name = ShoppingItemConstants.ITEM_NAMES_RAW[i];
            imageId = ShoppingItemConstants.ICON_IDS[i];
            items.add(new ShoppingItem(id, name, imageId));
        }
        return items;
    }

    public static boolean getCheckedStatus(int id) {
        boolean checked = true;
        String key = KEY_ID_PREFIX + id;
        if (MainActivity.preferences != null) {
            checked = MainActivity.preferences.getBoolean(key, false);
        }
        return checked;
    }

    public static void setCheckedStatus(int id, boolean checked) {
        if (MainActivity.preferences != null) {
            String key = KEY_ID_PREFIX + id;
            SharedPreferences.Editor editor = MainActivity.preferences.edit();
            if (!checked) {
                editor.remove(key);
            } else {
                editor.putBoolean(key, checked);
            }
            editor.apply();
        }
    }
}
