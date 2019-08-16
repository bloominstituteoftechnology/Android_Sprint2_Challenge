package com.lambdaschool.sprint2_challenge

import android.graphics.drawable.Drawable
import java.io.Serializable

data class ShoppingList(var name: String = "",
                        var image: Drawable): Serializable
