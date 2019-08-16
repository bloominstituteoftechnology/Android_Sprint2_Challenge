package com.lambdaschool.sprint2_challenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sprint2_challenge.model.FoodData
import com.lambdaschool.sprint2_challenge.R
import kotlinx.android.synthetic.main.item_display.view.*

class FoodListAdapter(val data: MutableList<FoodData>) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val image = view.food_icon
        val food_name = view.food_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_display, parent, false)

        view.setOnClickListener {
            view.setBackgroundColor(parent.context.resources.getColor(R.color.entryHighlight))

        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageURI(data[position].foodIcon)
        holder.food_name.text = data[position].foodName
    }
}