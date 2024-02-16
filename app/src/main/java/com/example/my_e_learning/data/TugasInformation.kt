package com.example.my_e_learning.data;

import androidx.annotation.DrawableRes

data class TugasInformation(
    val id: Int,
    val decription: String,
    @DrawableRes
    val image: Int?) {
}

