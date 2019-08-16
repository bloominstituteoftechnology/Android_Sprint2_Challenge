package com.lambdaschool.sprint2_challenge

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.shopping_list_item.view.*

class ShoppingListAdapter(val data: MutableList<ShoppingList>):
        RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name = view.textView2.toString()
        var image = view.image_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.shopping_list_item, parent, false)
        return ViewHolder(viewGroup)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name = data[position].name

        holder.image.setImageDrawable(data[position].image)
    }

    override fun getItemCount(): Int {
        return data.size

    }


}