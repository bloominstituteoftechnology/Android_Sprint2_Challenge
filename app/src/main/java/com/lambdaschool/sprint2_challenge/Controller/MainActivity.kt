package com.lambdaschool.sprint2_challenge.Controller

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lambdaschool.sprint2_challenge.Adapter.ProductsAdapter
import com.lambdaschool.sprint2_challenge.Model.Product
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.Shopping_items.ShoppingItemConstants
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.product_item.*
import javax.net.ssl.ManagerFactoryParameters

class MainActivity : AppCompatActivity() {

        companion object {
                const val NOTIF_KEY = 5
                const val NOTIF_Stringkey = "300"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_products)

            val products = mutableListOf<Product>()

            val productImage: Drawable? = ContextCompat.getDrawable(this, R.drawable.apple2)
            if (productImage != null) {
                   // products.add(Product("apple", productImage))
            }



            val manager = (GridLayoutManager(this,5))
            val adapter = ProductsAdapter(products)
            productListView.layoutManager = manager
            productListView.adapter = adapter






            //notification button code below
            //notification button code below


            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

            NOTI_btn.setOnClickListener { p0 ->

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                            val channelId = "channel_ID"
                            val name = "productNotification"
                            val importance = NotificationManager.IMPORTANCE_HIGH
                            val description = "this is the discription"
                            val channel = NotificationChannel(channelId, name, importance)
                            channel.description = description

                            notificationManager.createNotificationChannel(channel)


                            val notif_builder = NotificationCompat.Builder(this, channelId)
                                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                                    .setContentTitle("CONFIRMATION")
                                    .setContentText("Your order has been placed!!!")
                                    .setSmallIcon(android.R.drawable.btn_star_big_on)
                                    .setColor(getColor(R.color.colorPrimary))
                                    .setDefaults(Activity.DEFAULT_KEYS_DIALER)

                            notificationManager.notify(NOTIF_KEY, notif_builder.build())

                            val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "Please place this order for me: apples, ect")
                                    type = "text/plain"
                            }
                            startActivity(sendIntent)


                    }


            }}
    }








