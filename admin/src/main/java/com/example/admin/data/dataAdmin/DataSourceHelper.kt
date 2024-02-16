package com.example.admin.data.dataAdmin

sealed class DataSourceHelper<out T> {
    data class Success<T>(val data: T) : DataSourceHelper<T>()
    data class Error(val errorMessage: String) : DataSourceHelper<Nothing>()
}


sealed class DataSourceVoid {
    object Success : DataSourceVoid()
    data class Error(val errorMessage: String) : DataSourceVoid()
}

