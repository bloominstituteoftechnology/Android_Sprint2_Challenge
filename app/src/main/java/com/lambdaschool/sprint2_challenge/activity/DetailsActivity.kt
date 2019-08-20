package com.lambdaschool.sprint2_challenge.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.model.FoodData
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object{
        const val ITEM_KEY = "kwjer"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()
        window.exitTransition = Explode()

        val item_data = intent.getSerializableExtra(ITEM_KEY) as FoodData
        image_zoom.setImageURI(item_data.foodIcon)
        text_description.text = item_data.foodName
        
    }
}
