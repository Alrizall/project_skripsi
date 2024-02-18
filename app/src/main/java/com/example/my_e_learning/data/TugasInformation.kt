package com.example.my_e_learning.data;

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class TugasInformation(
    @SerializedName("description")
    var description: String?,

    ) {
    constructor() : this(
        null
    )
}