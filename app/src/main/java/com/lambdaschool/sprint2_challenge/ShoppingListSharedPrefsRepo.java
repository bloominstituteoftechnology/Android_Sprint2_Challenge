package com.lambdaschool.sprint2_challenge;

import android.content.Context;
import android.content.SharedPreferences;

public class ShoppingListSharedPrefsRepo {
    private static final String LIST_PREFERENCES = "ListPreferences";
    private SharedPreferences prefs;

    public ShoppingListSharedPrefsRepo(Context context) {
        prefs = context.getSharedPreferences(LIST_PREFERENCES,Context.MODE_PRIVATE);

    }

}
