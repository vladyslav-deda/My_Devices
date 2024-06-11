package com.ezlo.mydevices.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ezlo.mydevices.data.models.DeviceDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {

    @Query("SELECT * FROM devices")
    fun getAllDevices(): Flow<List<DeviceDbModel>>

    @Query("SELECT * FROM devices WHERE pkDevice = :id")
    fun getDeviceById(id: Long): DeviceDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevice(device: DeviceDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevices(devices: List<DeviceDbModel>)

    @Query("DELETE FROM devices")
    fun deleteAllDevices()

    @Query("DELETE FROM devices WHERE pkDevice = :id")
    fun deleteDeviceById(id: Long)
}