package com.lambdaschool.sprint2_challenge

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.shopping_list_item.view.*

class ShoppingListAdapter(val data: MutableList<ShoppingItemConstants>):
        RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name = view.checked_text.toString()
        var image = view.image_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(viewType,parent, false)
        return ViewHolder(viewGroup)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name = data[position].ITEM_NAMES_RAW.toString()

        holder.image.setImageResource(data[position].ICON_IDS[0])
    }

    override fun getItemCount(): Int {
        return data.size

    }


}