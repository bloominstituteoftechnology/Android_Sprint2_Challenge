package com.lambdaschool.sprint2_challenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import static com.lambdaschool.sprint2_challenge.ShoppingItemConstants.ICON_IDS;
import static com.lambdaschool.sprint2_challenge.ShoppingItemConstants.ITEM_NAMES_RAW;

public class MainActivity extends AppCompatActivity {
    ArrayList<ShoppingItem> itemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < ICON_IDS.length; ++i) { // generate itemList, ready to be displayed
            itemList.add (new ShoppingItem(ITEM_NAMES_RAW[i],ICON_IDS[i],i));
        }



    }
}
