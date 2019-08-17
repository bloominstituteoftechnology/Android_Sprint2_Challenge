package com.lambdaschool.sprint2_challenge.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.adapter.FoodListAdapter
import com.lambdaschool.sprint2_challenge.model.FoodData
import com.lambdaschool.sprint2_challenge.utility.Notification
import com.lambdaschool.sprint2_challenge.utility.ShoppingItemConstants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_display.view.*

class MainActivity : AppCompatActivity() {


    companion object {
        val foodList = mutableListOf<FoodData>()
        val foodListAdapter = FoodListAdapter(foodList)
        val selectedFoodItems = mutableListOf<View>()
        val selectedFoodItemsIndex = mutableListOf<Int>()
        const val SHOPPING_CART = 298324
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        grocery_list.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        grocery_list.layoutManager = manager
        grocery_list.adapter = foodListAdapter

        val imageUri = "android.resource://${this.packageName}/drawable/"

        for(i in 0..ShoppingItemConstants.SIZE){
            print(imageUri + getString(ShoppingItemConstants.ICON_IDS[i]))
            foodList.add(FoodData(ShoppingItemConstants.ITEM_NAMES_RAW[i], Uri.parse(imageUri + ShoppingItemConstants.ITEM_NAMES_RAW[i]), i))
        }

        foodListAdapter.notifyDataSetChanged()

        send_list_btn.setOnClickListener {
            val notification = Notification(this, MainActivity::class.java, R.drawable.ic_nofication)
            notification.notify(SHOPPING_CART, "Confirmation", "Your order has been placed.")

            var message = getString(R.string.notification_start)
            message += " "

            selectedFoodItems.forEachIndexed {
                index, it ->
                if(index != selectedFoodItems.size-1){
                    message += it.food_name.text.toString() + ", "
                } else {
                    message += it.food_name.text.toString() + "."
                }
                foodList.removeAt(selectedFoodItemsIndex[index])
            }
            
            foodListAdapter.notifyDataSetChanged()
            val sharingIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(message)
                .getIntent()
            if(sharingIntent.resolveActivity(this.packageManager) != null){
                startActivity(sharingIntent)
            }
        }
    }
}
