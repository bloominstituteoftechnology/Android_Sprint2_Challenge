package com.lambdaschool.sprint2_challenge.Adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lambdaschool.sprint2_challenge.Model.Product
import com.lambdaschool.sprint2_challenge.R

import kotlinx.android.synthetic.main.product_item.view.*



class ProductAdapter( val products: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductHolder>(){

    override fun getItemCount(): Int {
        return products.size
    }

    /*override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ProductHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.product_item, p0, false)
        return ProductHolder(view)
    } */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent,false) as View)
    }
/*
    override fun onBindViewHolder(holder: ProductHolder?, position: Int) {
        holder?.bindProduct(products[position], context)
    } */
override fun onBindViewHolder(holder: ProductHolder, position: Int) {
   val grocery = products[position]
    holder.bindModel(grocery)

    holder.listParent.setOnClickListener{
        grocery.purchased = !grocery.purchased
        notifyItemChanged(position)

    }
}


    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productImage: ImageView = itemView.productimage
        val productName: TextView = itemView.productName
        val listParent: LinearLayout = itemView.list_of_items


        fun bindModel(product: Product) {
            productImage.setImageResource(product.imageId)
            productName.text = product.title
            if (product.purchased)
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorAccent))
            else
                listParent.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorPrimary))


        }
    }
}







