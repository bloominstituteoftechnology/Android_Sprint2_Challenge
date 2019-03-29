package com.lambdaschool.sprint2_challenge;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<ShoppingItem> shoppingItemArrayList;
    private ViewGroup parentLayout;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLayout = findViewById(R.id.constraint_layout_parent);
        imageView = findViewById(R.id.image_view_icon);
        textView = findViewById(R.id.text_view_name);
        ShoppingCart.initializeSharedPreferences(this);
        shoppingItemArrayList = ShoppingItemFactory.getShoppingItems();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(shoppingItemArrayList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shoppingListAdapter);
        recyclerView.setHasFixedSize(true);
        //recyclerView.findViewHolderForAdapterPosition(8).itemView.performClick();
        toggleBulkItems(ShoppingCart.getSharedPreferences(), recyclerView);
    }


    public static void toggleBulkItems(String shoppingItemsAddedToCart, RecyclerView recyclerView) {

        if (shoppingItemsAddedToCart != null && !shoppingItemsAddedToCart.equals("")) {
            String[] shoppingItemIds = shoppingItemsAddedToCart.split(",");
            ArrayList<String> convertedStringArray = new ArrayList<>(Arrays.asList(shoppingItemIds));
            for (int i = 0; i < shoppingItemArrayList.size(); i++) {
                ShoppingItem itemToCheck = shoppingItemArrayList.get(i);
                if (convertedStringArray.contains(Integer.toString(itemToCheck.getShoppingItemId()))) {
                    shoppingItemArrayList.get(i).setShoppingItemInCart(true);
                }
            }
        }
    }

/*    private static void toggleIndividualItems(view) {
        int color;
        if (ShoppingCart.isItemInTheShoppingCart(Integer.toString(item.getShoppingItemId()))) {
            ShoppingCart.removeItemFromShoppingCart(Integer.toString(item.getShoppingItemId()));
            //viewHolder.switchToggle.setChecked(false);
            color = R.color.cardview_light_background;
        } else {
            ShoppingCart.addItemToShoppingCart(Integer.toString(item.getShoppingItemId()));
            //viewHolder.switchToggle.setChecked(true);
            color = R.color.colorAccent;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            viewHolder.parentLayout.setBackgroundColor(context.getColor(color));
        else
            viewHolder.parentLayout.setBackgroundColor(context.getResources().getColor(color));
    }*/
}
