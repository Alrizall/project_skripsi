package com.example.admin.data.dataAdmin

import android.content.Context
import android.content.Intent
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val mFirebaseAuth: FirebaseAuth
) :
    ProfileRemoteDataSource {

    override suspend fun signUp(email: String, password: String): DataSourceHelper<AuthenticatedUserInfo> {
        val result: CompletableDeferred<DataSourceHelper<AuthenticatedUserInfo>> =
            CompletableDeferred()

        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mFirebaseAuth.currentUser
                    // Sign in success, update UI with the signed-in user's information
                    result.complete(DataSourceHelper.Success(FirebaseUserInfo(user)))
                } else {
                    // If sign in fails, display a message to the user.
                    result.complete(DataSourceHelper.Error("Failure : ${task.exception}"))
                }
            }


        return result.await()
    }

    override suspend fun signIn(email: String, password: String): DataSourceVoid {
        val result: CompletableDeferred<DataSourceVoid> =
            CompletableDeferred()

        mFirebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    result.complete(DataSourceVoid.Success)
                } else {
                    // If sign in fails, display a message to the user.
                    result.complete(DataSourceVoid.Error("Failure : ${task.exception}"))
                }
            }

        return result.await()
    }

    override suspend fun sendEmailVerification(firebaseUser: FirebaseUser?): DataSourceVoid {
        val result: CompletableDeferred<DataSourceVoid> =
            CompletableDeferred()

        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification()
                .addOnCompleteListener { task ->
                    // Email Verification sent
                    if (task.isSuccessful) {
                        result.complete(DataSourceVoid.Success)
                    } else {
                        result.complete(DataSourceVoid.Error("Failure : ${task.exception}"))
                    }
                }
        } else {
            result.complete(DataSourceVoid.Error("Failure : user is null"))
        }
        return result.await()
    }

    override fun getUserProfile(): Flow<DataSourceHelper<AuthenticatedUserInfo>> {
        return mFirebaseAuth.valueEventProfileFlow().map { auth ->
            if (auth.currentUser != null) {
                DataSourceHelper.Success(FirebaseUserInfo(auth.currentUser))
            } else {
                DataSourceHelper.Error("User not login")
            }
        }.catch {
            DataSourceHelper.Error("User not login because ${it.message}")
        }
    }

    override fun initLogout(onComplete: () -> Unit) {
        AuthUI.getInstance()
            .signOut(context)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onComplete()
                } else {
                    Log.e("TAG", "initLogout: failed logout :${it.exception}")
                }

            }
    }

}