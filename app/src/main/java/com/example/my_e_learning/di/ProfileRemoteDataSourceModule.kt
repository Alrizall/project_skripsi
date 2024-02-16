package com.example.my_e_learning.di

import com.example.my_e_learning.data.dataUser.ProfileRemoteDataSource
import com.example.my_e_learning.data.dataUser.ProfileRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface ProfileRemoteDataSourceModule {
    @Binds
    fun bindsProfileRemoteDataSource(
        impl: ProfileRemoteDataSourceImpl
    ): ProfileRemoteDataSource
}