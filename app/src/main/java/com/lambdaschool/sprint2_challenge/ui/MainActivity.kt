package com.lambdaschool.sprint2_challenge.ui
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.utils.ShoppingItemConstants
import com.lambdaschool.sprint2_challenge.model.ShoppingItem
import java.util.*

class MainActivity : AppCompatActivity() {

    internal var shoppingList: ArrayList<ShoppingItem> = ArrayList()
    private val adapter = ShoppingListAdapter(shoppingList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val shoppingListLayout=findViewById<RecyclerView>(R.id.shopping_list_layout)
        shoppingListLayout.setHasFixedSize(false)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        shoppingListLayout.layoutManager = manager
        shoppingListLayout.adapter = adapter

        for (i in ShoppingItemConstants.ICON_IDS) {
            shoppingList.add(ShoppingItem(ShoppingItemConstants.ITEM_NAMES_RAW[i], ShoppingItemConstants.ICON_IDS[i], 1))

        }
        adapter.notifyDataSetChanged()
    }
}
