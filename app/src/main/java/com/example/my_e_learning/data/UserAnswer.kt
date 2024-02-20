package com.example.my_e_learning.data

import com.google.gson.annotations.SerializedName

data class UserAnswer(
    @SerializedName("id")
    var id: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("answer")
    var answer: String?
) {

    constructor() : this(
        null,
        null,
        null
    )

}