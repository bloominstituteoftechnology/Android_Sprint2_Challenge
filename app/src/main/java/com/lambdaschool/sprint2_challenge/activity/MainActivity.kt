package com.lambdaschool.sprint2_challenge.activity

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.adapter.FoodListAdapter
import com.lambdaschool.sprint2_challenge.model.FoodData
import com.lambdaschool.sprint2_challenge.utility.ShoppingItemConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val foodList = mutableListOf<FoodData>()
    private val foodListAdapter = FoodListAdapter(foodList)

    public val selectedFoodItems = mutableListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grocery_list.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        grocery_list.layoutManager = manager
        grocery_list.adapter = foodListAdapter

        val imageUri = "android.resource://{${this.packageName}/drawable/"

        for(i in 0..ShoppingItemConstants.SIZE){
            foodList.add(FoodData(ShoppingItemConstants.ITEM_NAMES_RAW[i], Uri.parse(imageUri + getString(ShoppingItemConstants.ICON_IDS[i])) ))
        }

        foodListAdapter.notifyDataSetChanged()
    }
}
