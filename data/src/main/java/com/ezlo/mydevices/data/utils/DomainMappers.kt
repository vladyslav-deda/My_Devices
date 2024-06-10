package com.ezlo.mydevices.data.utils

import com.ezlo.mydevices.data.models.DeviceDto
import com.ezlo.mydevices.domain.models.Device

fun List<DeviceDto>.toDomain() = this.map {
    it.toDomain()
}

fun DeviceDto.toDomain() =
    this.run {
        Device(
            pkDevice = pkDevice ?: 0,
            macAddress = macAddress ?: "",
            pkDeviceType = pkDeviceType ?: 0,
            pDeviceSubType = pDeviceSubType ?: 0,
            firmware = firmware ?: "",
            serverDevice = serverDevice ?: "",
            serverEvent = serverEvent ?: "",
            serverAccount = serverAccount ?: "",
            internalIP = internalIP ?: "",
            lastAliveReported = lastAliveReported ?: "",
            platform = platform ?: ""
        )
    }