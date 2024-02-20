package com.example.my_e_learning.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MateriInformation (
    @SerializedName("description1")
    var description1: String?,
    @SerializedName("description2")
    var description2: String?,
    @SerializedName("image")
    var image: String?
) : Parcelable {
    constructor() : this(
        null,
        null,
        null,
    )
}