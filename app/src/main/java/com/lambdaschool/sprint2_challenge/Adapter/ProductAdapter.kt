package com.lambdaschool.sprint2_challenge.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lambdaschool.sprint2_challenge.Model.Product
import com.lambdaschool.sprint2_challenge.R

import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter (val products: MutableList<Product>) :RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.productName
        val image: ImageView =view.productimage

    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
       val view = LayoutInflater.from(p0.context).inflate(R.layout.activity_products,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = products[position].title
        holder.image.setImageDrawable(products[position].image)
    }






        }



