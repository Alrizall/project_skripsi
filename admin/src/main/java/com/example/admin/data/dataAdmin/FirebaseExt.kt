package com.example.admin.data.dataAdmin

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow


fun FirebaseAuth.valueEventProfileFlow(): Flow<FirebaseAuth> = callbackFlow {
    val authStateListener = FirebaseAuth.AuthStateListener { auth ->
        trySendBlocking(auth).getOrThrow()
    }
    addAuthStateListener(authStateListener)
    awaitClose {
        removeAuthStateListener(authStateListener)
    }
}

