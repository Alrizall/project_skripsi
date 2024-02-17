package com.example.my_e_learning.data.model

import com.example.my_e_learning.data.UserInformation
import com.example.my_e_learning.data.dataUser.AuthenticatedUserInfo

data class ProfileUiState(
    val errorMessage: String,
    val user: UserInformation?
) {
    companion object {
        fun initial() = ProfileUiState(
            errorMessage = "",
            user = null
        )
    }
}