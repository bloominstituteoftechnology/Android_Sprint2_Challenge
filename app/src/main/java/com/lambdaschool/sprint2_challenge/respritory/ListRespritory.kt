package com.lambdaschool.sprint2_challenge.respritory

import com.lambdaschool.sprint2_challenge.respritory.ShoppingConstants.ICON_IDS
import com.lambdaschool.sprint2_challenge.respritory.ShoppingConstants.ITEM_NAMES_RAW

class ListRespritory {
    companion object {


        var createList = mutableListOf<ShoppingList>()
        // TODO combine into one list

        fun createView(){
            for(index in 0 until ITEM_NAMES_RAW.size)
                createList.add(ShoppingList(ITEM_NAMES_RAW[index], ICON_IDS[index], isChecked = false))
        }

    }
}