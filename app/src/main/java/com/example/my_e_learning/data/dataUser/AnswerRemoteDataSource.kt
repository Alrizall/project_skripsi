package com.example.my_e_learning.data.dataUser

import com.example.my_e_learning.data.UserAnswer

interface AnswerRemoteDataSource {
    suspend fun uploadAnswerData(
        data: UserAnswer,
    ): DataSourceVoid
}