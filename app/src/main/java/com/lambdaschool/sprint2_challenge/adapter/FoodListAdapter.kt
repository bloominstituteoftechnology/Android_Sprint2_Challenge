package com.lambdaschool.sprint2_challenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sprint2_challenge.model.FoodData
import com.lambdaschool.sprint2_challenge.R
import com.lambdaschool.sprint2_challenge.activity.MainActivity
import kotlinx.android.synthetic.main.item_display.view.*

class FoodListAdapter(val data: MutableList<FoodData>) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>(){

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val image = view.food_icon
        val food_name = view.food_name
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
        holder.food_name.text = data[position].foodName

        holder.view.setOnClickListener {
            if(!MainActivity.selectedFoodItems.contains(holder.view)){
                holder.view.setBackgroundColor(holder.view.context.resources.getColor(R.color.entryHighlight))
                MainActivity.selectedFoodItems.add(holder.view)
                MainActivity.selectedFoodItemsId.add(data[position].Id)
            } else{
                holder.view.setBackgroundColor(holder.view.context.resources.getColor(R.color.white))
                MainActivity.selectedFoodItems.remove(holder.view)
                MainActivity.selectedFoodItemsId.remove(data[position].Id)
            }
        }
    }
}