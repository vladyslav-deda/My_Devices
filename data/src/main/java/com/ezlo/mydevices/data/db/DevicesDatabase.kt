package com.ezlo.mydevices.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezlo.mydevices.data.models.DeviceDbModel

@Database(entities = [DeviceDbModel::class], version = 1)
abstract class DevicesDatabase : RoomDatabase() {
    abstract fun devicesDao(): DeviceDao
}