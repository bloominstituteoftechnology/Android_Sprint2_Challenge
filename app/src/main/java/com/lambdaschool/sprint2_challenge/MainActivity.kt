package com.lambdaschool.sprint2_challenge

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.widget.LinearLayoutManager
import com.lambdaschool.sprint2_challenge.GroceryRepository.Companion.groceryList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.grocery_list_item.*
import android.content.Intent



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GroceryRepository.creategrocerylist()

        food_list.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = GroceryAdapter(GroceryRepository.groceryList)
        }

        send_list.setOnClickListener {
            createNotification("Your order has been placed")
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, getFavorites());
            startActivity(Intent.createChooser(shareIntent, getFavorites()))

        }



    }

    fun getFavorites(): String {
        var favoritesString = ""
        for (food in GroceryRepository.groceryList) {
            if (food.Isselected) favoritesString += "please place this order for me ${food.name}, "
        }

        return favoritesString
    }

    fun createNotification(favorites: String) {
        val channelId = "${this.packageName}.simplechannel"
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Food Notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = "Channel to send food notification"

            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("confirmation")
                .setContentText(favorites)
                .setAutoCancel(true)
        notificationManager.notify(1, notificationBuilder.build())
    }
}


