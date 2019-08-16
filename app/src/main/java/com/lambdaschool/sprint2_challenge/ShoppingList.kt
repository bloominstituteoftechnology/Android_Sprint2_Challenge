package com.lambdaschool.sprint2_challenge

import android.graphics.drawable.Drawable
import android.widget.CheckBox
import java.io.Serializable

data class ShoppingList(var name: String = "",
                        var image: Drawable,
                        var checkBox:  Boolean = false): Serializable
