package com.ezlo.mydevices.data.repositories

import com.ezlo.mydevices.data.db.DeviceDao
import com.ezlo.mydevices.data.utils.DevicesMappers
import com.ezlo.mydevices.domain.models.Device
import com.ezlo.mydevices.domain.repositories.LocalDevicesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDevicesRepositoryImpl @Inject constructor(
    private val deviceDao: DeviceDao,
) : LocalDevicesRepository {

    override val allDevices: Flow<List<Device>> =
        deviceDao
            .getAllDevices()
            .map {
                DevicesMappers.mapDeviceDbModelToDomain(it)
            }

    override suspend fun getDevicesById(deviceId: Int): Device? {
        val result = deviceDao.getDeviceById(deviceId) ?: return null
        return DevicesMappers.mapDeviceFromDbModel(result)
    }

    override suspend fun insertDevice(device: Device) {
        deviceDao.insertDevice(DevicesMappers.mapDeviceToDbModel(device))
    }

    override suspend fun insertDevices(devices: List<Device>) {
        val mappedDevices = devices.map {
            DevicesMappers.mapDeviceToDbModel(it)
        }
        deviceDao.insertDevices(mappedDevices)
    }

    override suspend fun deleteAllDevices() {
        deviceDao.deleteAllDevices()
    }

    override suspend fun deleteDeviceById(deviceId: Int) {
        deviceDao.deleteDeviceById(deviceId)
    }
}