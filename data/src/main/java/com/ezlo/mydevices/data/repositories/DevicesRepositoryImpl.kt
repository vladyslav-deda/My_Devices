package com.ezlo.mydevices.data.repositories

import com.ezlo.mydevices.data.network.DevicesService
import com.ezlo.mydevices.data.utils.DevicesMappers
import com.ezlo.mydevices.domain.models.Device
import com.ezlo.mydevices.domain.repositories.DevicesRepository
import retrofit2.await
import javax.inject.Inject

class DevicesRepositoryImpl @Inject constructor(
    private val devicesService: DevicesService,
) : DevicesRepository {

    override suspend fun retrieveDevicesList(): List<Device> {
        val result = devicesService.getDevicesList().await()
        val list = result.devices ?: emptyList()
        return DevicesMappers.mapDeviceDtoToDomain(list)
    }
}