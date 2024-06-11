package com.ezlo.mydevices.data.di

import com.ezlo.mydevices.data.repositories.DevicesRepositoryImpl
import com.ezlo.mydevices.data.repositories.LocalDevicesRepositoryImpl
import com.ezlo.mydevices.data.utils.DevicesMappers
import com.ezlo.mydevices.domain.repositories.DevicesRepository
import com.ezlo.mydevices.domain.repositories.LocalDevicesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DevicesModule {
    @Binds
    fun bindDevicesRepository(impl: DevicesRepositoryImpl): DevicesRepository

    @Binds
    fun bindLocalDevicesRepository(impl: LocalDevicesRepositoryImpl): LocalDevicesRepository
}