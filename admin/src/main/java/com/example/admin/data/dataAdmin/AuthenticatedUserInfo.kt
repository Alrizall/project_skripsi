package com.example.admin.data.dataAdmin


interface AuthenticatedUserInfo {

    fun getEmail(): String?

    fun getDisplayName(): String?

    fun getPhotoUrl(): String?

    fun getUid():String?

}