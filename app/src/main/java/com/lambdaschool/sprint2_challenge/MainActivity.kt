package com.lambdaschool.sprint2_challenge

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.CheckBox
import android.widget.Toast
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
                if (almond != null) {
                    data.add(ShoppingList("Almond", almond))
            }
        val greenApple = ContextCompat.getDrawable(this, R.drawable.apple_green)
                if(greenApple != null){
                    data.add(ShoppingList("Green Apple", greenApple))
                }
        val avocado = ContextCompat.getDrawable(this, R.drawable.avocado)
        if (avocado != null) {
            data.add(ShoppingList("Avocado", avocado))
        }
        val bacon = ContextCompat.getDrawable(this, R.drawable.bacon)
        if(bacon != null){
            data.add(ShoppingList("Bacon", bacon))
        }
        val banana = ContextCompat.getDrawable(this, R.drawable.banana)
        if (banana != null) {
            data.add(ShoppingList("Banana", banana))
        }
        val barley = ContextCompat.getDrawable(this, R.drawable.barley)
        if(barley != null){
            data.add(ShoppingList("Barley", barley))
        }
        val beef = ContextCompat.getDrawable(this, R.drawable.beef)
        if (beef != null) {
            data.add(ShoppingList("Beef", beef))
        }
        val birthdayCake = ContextCompat.getDrawable(this, R.drawable.birthday_cake)
        if(birthdayCake != null){
            data.add(ShoppingList("Birthday Cake", birthdayCake))
        }
        val broccoli = ContextCompat.getDrawable(this, R.drawable.broccoli)
        if (broccoli != null) {
            data.add(ShoppingList("Broccoli", broccoli))
        }
        val candy = ContextCompat.getDrawable(this, R.drawable.candy)
        if(candy != null){
            data.add(ShoppingList("Candy", candy))
        }
        val cherry = ContextCompat.getDrawable(this, R.drawable.cherry)
        if (cherry != null) {
            data.add(ShoppingList("Cherry", cherry))
        }
        val chicken = ContextCompat.getDrawable(this, R.drawable.chicken)
        if(chicken != null){
            data.add(ShoppingList("Chicken", chicken))
        }





        //populate almond
            val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            val adapter = ShoppingListAdapter(data)
            recycle.layoutManager = manager
            recycle.adapter = adapter


        }
    }

