package com.example.my_e_learning.di

import com.example.my_e_learning.data.dataUser.MateriRemoteDataSource
import com.example.my_e_learning.data.dataUser.MateriRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MateriRemoteDataSourceModule {

    @Binds
    fun bindsMateriRemoteDataSource(impl: MateriRemoteDataSourceImpl): MateriRemoteDataSource
}