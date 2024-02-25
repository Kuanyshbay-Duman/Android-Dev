package kz.sd.bobo.model

import androidx.annotation.DrawableRes

data class Pizza(
    val title:String,
    @DrawableRes val img:Int,
    val desc:String,
    val price:Int,
    val size:String,
)
