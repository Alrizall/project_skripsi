package com.example.my_e_learning.data

import androidx.annotation.DrawableRes

data class DashboardInformation(
    val id : Int,
    @DrawableRes
    val image : Int,
    val title : String
)
