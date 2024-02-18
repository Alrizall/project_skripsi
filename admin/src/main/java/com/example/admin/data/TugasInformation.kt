package com.example.admin.data;

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

