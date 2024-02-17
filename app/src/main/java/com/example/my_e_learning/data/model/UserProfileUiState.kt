package com.example.my_e_learning.data.model

import com.example.my_e_learning.data.dataUser.AuthenticatedUserInfo

data class UserProfileUiState(
    val errorMessage: String,
    val user: AuthenticatedUserInfo?
) {
    companion object {
        fun initial() = UserProfileUiState(
            errorMessage = "",
            user = null
        )
    }
}