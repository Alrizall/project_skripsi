package com.example.my_e_learning.data.dataUser

import android.content.Intent
import com.google.firebase.auth.FirebaseUser
import com.example.my_e_learning.data.dataUser.AuthenticatedUserInfo
import com.example.my_e_learning.data.dataUser.DataSourceHelper
import com.example.my_e_learning.data.dataUser.DataSourceVoid
import kotlinx.coroutines.flow.Flow

interface ProfileRemoteDataSource {

    suspend fun signUp(email: String, password: String): DataSourceVoid

    suspend fun signIn(email: String, password: String): DataSourceVoid

    suspend fun sendEmailVerification(firebaseUser: FirebaseUser?): DataSourceVoid

    fun getUserProfile(): Flow<DataSourceHelper<AuthenticatedUserInfo>>

    fun initLogout(onComplete: () -> Unit)
}