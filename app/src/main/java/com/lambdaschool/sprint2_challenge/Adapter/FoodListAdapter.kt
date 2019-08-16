package com.lambdaschool.sprint2_challenge.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sprint2_challenge.Model.FoodData

class FoodListAdapter(val data: MutableList<FoodData>) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        
    }
}