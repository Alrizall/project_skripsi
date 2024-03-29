package com.example.admin.data.dataAdmin

import com.google.firebase.auth.FirebaseUser

open class FirebaseUserInfo(
    private val firebaseUser: FirebaseUser?
) : AuthenticatedUserInfo {

    override fun getEmail(): String? = firebaseUser?.email

    override fun getDisplayName(): String? = firebaseUser?.displayName

    override fun getPhotoUrl(): String? = firebaseUser?.photoUrl.toString()

    override fun getUid(): String? = firebaseUser?.uid
}
