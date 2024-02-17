package com.example.admin.data

import com.google.gson.annotations.SerializedName

data class MateriInformation(
    @SerializedName("description1")
    var description1: String?,
    @SerializedName("description2")
    var description2: String?,
    @SerializedName("image")
    var image: String?
) {
    constructor() : this(
        null,
        null,
        null,
    )
}