package com.ezlo.mydevices.domain.repositories

import com.ezlo.mydevices.domain.models.Device
import kotlinx.coroutines.flow.Flow

interface LocalDevicesRepository {

    val allDevices: Flow<List<Device>>

    suspend fun getDevicesById(deviceId: Int): Device?

    suspend fun insertDevice(device: Device)

    suspend fun insertDevices(devices: List<Device>)

    suspend fun deleteAllDevices()

    suspend fun deleteDeviceById(deviceId: Int)
}