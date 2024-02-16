package com.example.my_e_learning.data.dataUser


interface AuthenticatedUserInfo {

    fun getEmail(): String?

    fun getDisplayName(): String?

    fun getPhotoUrl(): String?

    fun getUid():String?

}