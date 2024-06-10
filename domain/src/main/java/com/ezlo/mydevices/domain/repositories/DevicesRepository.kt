package com.ezlo.mydevices.domain.repositories

import com.ezlo.mydevices.domain.models.Device

interface DevicesRepository {
    suspend fun retrieveDevicesList(): List<Device>
}