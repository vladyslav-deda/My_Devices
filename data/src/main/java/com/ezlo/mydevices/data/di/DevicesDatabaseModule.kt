package com.ezlo.mydevices.data.di

import android.content.Context
import androidx.room.Room
import com.ezlo.mydevices.data.db.DeviceDao
import com.ezlo.mydevices.data.db.DevicesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DevicesDatabaseModule {

    private const val DB_NAME = "Devices"

    @Provides
    fun provideDeviceDao(devicesDatabase: DevicesDatabase): DeviceDao {
        return devicesDatabase.devicesDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): DevicesDatabase {
        return Room
            .databaseBuilder(
                appContext,
                DevicesDatabase::class.java,
                DB_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

}