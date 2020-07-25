package com.lambdaschool.sprint2_challenge.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sprint2_challenge.model.FoodData
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.activity.DetailsActivity
import com.lambdaschool.sprint2_challenge.activity.MainActivity
import kotlinx.android.synthetic.main.item_display.view.*

class FoodListAdapter(private val data: MutableList<FoodData>) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val card_view = view.card_view
        val image = view.food_icon
        val food_name = view.food_name
    }

    private fun enterAnimation(holder: ViewHolder, position: Int){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_display, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageURI(data[position].foodIcon)
        holder.food_name.text = data[position].foodName.replace("_", " ").replace("[^A-Za-z ]", "")

//        holder.view.setOnClickListener {
//            if(!MainActivity.selectedFoodItems.contains(holder.view)){
//                holder.view.setBackgroundColor(holder.view.context.resources.getColor(R.color.entryHighlight))
//                MainActivity.selectedFoodItems.add(holder.view)
//                MainActivity.selectedFoodItemsId.add(data[position].Id)
//            } else{
//                holder.view.setBackgroundColor(holder.view.context.resources.getColor(R.color.white))
//                MainActivity.selectedFoodItems.remove(holder.view)
//                MainActivity.selectedFoodItemsId.remove(data[position].Id)
//            }
//        }

        holder.card_view.setOnClickListener { view ->  
            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.ITEM_KEY, data[position])

            val optionsBundle: Bundle = ActivityOptions.makeSceneTransitionAnimation(view.context as Activity).toBundle()

            view.context.startActivity(intent, optionsBundle)
        }
    }
}