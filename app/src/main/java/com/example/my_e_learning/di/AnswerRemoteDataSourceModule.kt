package com.example.my_e_learning.di

import com.example.my_e_learning.data.dataUser.AnswerRemoteDataSource
import com.example.my_e_learning.data.dataUser.AnswerRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn (SingletonComponent ::class)
interface AnswerRemoteDataSourceModule {

    @Binds
    fun bindsAnswerRemoteDataSOurce (impl: AnswerRemoteDataSourceImpl): AnswerRemoteDataSource
}