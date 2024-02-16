package com.example.admin.data.dataAdmin

import android.content.Intent
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface ProfileRemoteDataSource {

    suspend fun signUp(email: String, password: String): DataSourceHelper<AuthenticatedUserInfo>

    suspend fun signIn(email: String, password: String): DataSourceVoid

    suspend fun sendEmailVerification(firebaseUser: FirebaseUser?): DataSourceVoid

    fun getUserProfile(): Flow<DataSourceHelper<AuthenticatedUserInfo>>

    fun initLogout(onComplete: () -> Unit)
}