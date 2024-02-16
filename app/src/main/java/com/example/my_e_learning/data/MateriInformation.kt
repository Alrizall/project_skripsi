package com.example.my_e_learning.data

import androidx.annotation.DrawableRes

data class MateriInformation (
    val id: Int,
    val decription1: String,
    val decription2: String?,
    @DrawableRes
    val image: Int,
    val title: String,
    val uri : String?){
}