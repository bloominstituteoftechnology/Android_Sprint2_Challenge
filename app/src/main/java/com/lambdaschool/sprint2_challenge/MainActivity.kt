package com.lambdaschool.sprint2_challenge

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shopping_list_item.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val NOTIFICATION_ID = 1

        const val CONSTANT_STRING = "CLICKS"
        const val CONSTANT_STRING2 = "ACTION BAR TEXT"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_add.setOnClickListener {

            //TODO call the notification heer
            BasicNotification.BasicNotification(this)


        }



        val data = mutableListOf<ShoppingList>()
        val almond = ContextCompat.getDrawable(this, R.drawable.almond)

        if (almond != null){
            data.add(ShoppingList("Almond", almond))
        }

        //populate almond
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = ShoppingListAdapter(data)
        recycle.layoutManager = manager
        recycle.adapter = adapter




    }

}
