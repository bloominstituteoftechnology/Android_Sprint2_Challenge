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
import kotlinx.android.synthetic.main.product_list_item.view.*

/*class ProductsAdapter (val products: MutableList<Product>) :RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

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


*/

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

    holder.productImage.setOnClickListener{
        grocery.purchased = !grocery.purchased
        notifyItemChanged(position)

    }
}


    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productImage: ImageView = itemView.productImage
        val productName: TextView = itemView.productName
        val listParent: LinearLayout = itemView.


        fun bindModel(product: Product) {
            productImage.setImageResource(product.imageId)
            productName.text = product.title
            if (product.purchased)
                list_of_items.setBackgroundColor(contextCompat.getColor(itemView.context,R.color.colorAccent))
            else
                list_of_items.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.colorPrimary))


        }
    }
}







