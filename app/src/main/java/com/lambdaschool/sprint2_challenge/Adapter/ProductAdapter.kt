package com.lambdaschool.sprint2_challenge.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lambdaschool.sprint2_challenge.Model.Product

class ProductAdapter (val context: Context,val products: List<Product>) :RecyclerView.Adapter<ProductAdapter.ProductHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
       return products.count()
    }

    override fun onBindViewHolder(p0: ProductHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productImage =itemView?.findViewById<ImageView>(R.id)


    }


}
