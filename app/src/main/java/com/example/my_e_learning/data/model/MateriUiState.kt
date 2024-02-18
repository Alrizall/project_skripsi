package com.example.my_e_learning.data.model

import com.example.my_e_learning.data.MateriInformation

data class MateriUiState(
    val errorMessage: String,
    val materi: List<MateriInformation>
) {
    companion object {
        fun initial() = MateriUiState(
            errorMessage = "",
            materi = emptyList()
        )
    }
}