package com.example.my_e_learning.data.model

import com.example.my_e_learning.data.MateriInformation
import com.example.my_e_learning.data.TugasInformation

data class TugasUiState(
    val errorMessage: String,
    val tugas: List<TugasInformation>
) {
    companion object {
        fun initial() = TugasUiState(
            errorMessage = "",
            tugas = emptyList()
        )
    }
}