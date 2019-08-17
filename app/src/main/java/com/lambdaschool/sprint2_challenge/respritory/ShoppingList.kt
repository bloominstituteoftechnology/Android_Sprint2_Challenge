package com.lambdaschool.sprint2_challenge.respritory

import java.io.Serializable

data class ShoppingList(var name: String = "Almond",
                        var image: Int,
                        var isChecked:  Boolean = false): Serializable
