package com.example.my_e_learning.data;

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TugasInformation(
    @SerializedName("description")
    var description: String?,

) : Parcelable {
    constructor() : this(
        null
    )
}

