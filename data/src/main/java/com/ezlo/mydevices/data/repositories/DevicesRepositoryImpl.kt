package com.ezlo.mydevices.data.repositories

import com.ezlo.mydevices.data.network.DevicesService
import com.ezlo.mydevices.data.utils.toDomain
import com.ezlo.mydevices.domain.models.Device
import com.ezlo.mydevices.domain.repositories.DevicesRepository
import retrofit2.awaitResponse
import javax.inject.Inject

class DevicesRepositoryImpl @Inject constructor(
    private val devicesService: DevicesService,
) : DevicesRepository {
    override suspend fun retrieveDevicesList(): List<Device> {
        val response = devicesService.getDevicesList().awaitResponse()
        return response.body()?.devices?.toDomain() ?: emptyList()
    }
}