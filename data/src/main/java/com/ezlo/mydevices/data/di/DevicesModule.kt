package com.ezlo.mydevices.data.di

import com.ezlo.mydevices.data.repositories.DevicesRepositoryImpl
import com.ezlo.mydevices.domain.repositories.DevicesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DevicesModule {
    @Binds
    fun bindDevicesRepository(impl: DevicesRepositoryImpl): DevicesRepository
}