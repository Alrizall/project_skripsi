package com.example.my_e_learning.di

import com.example.my_e_learning.data.dataUser.TugasRemoteDataSource
import com.example.my_e_learning.data.dataUser.TugasRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TugasRemoteDataSourceModule {

    @Binds
    fun bindsTugasRemoteDataSource(impl: TugasRemoteDataSourceImpl): TugasRemoteDataSource
}