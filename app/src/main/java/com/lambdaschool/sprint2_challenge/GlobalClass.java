package com.lambdaschool.sprint2_challenge;

import android.app.Application;
import android.content.Context;

public class GlobalClass extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
