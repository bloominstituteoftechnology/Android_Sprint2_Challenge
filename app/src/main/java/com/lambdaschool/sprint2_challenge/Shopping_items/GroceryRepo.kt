package com.lambdaschool.sprint2_challenge.Shopping_items

import com.lambdaschool.sprint2_challenge.Model.Product

class GroceryRepo {
    companion object {
        val GroceryList = mutableListOf<Product>()
        fun createGroceryList() {

            for (i in 0 until groceryitems.size ) {
                GroceryList.add(Product(groceryitems[i],groceryIds[i],purchase false )
            }
        }
    }
}