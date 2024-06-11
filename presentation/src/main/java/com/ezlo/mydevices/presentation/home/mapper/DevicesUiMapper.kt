package com.ezlo.mydevices.presentation.home.mapper

import com.ezlo.mydevices.domain.models.Device
import com.ezlo.mydevices.presentation.home.models.DeviceUiModel
import com.ezlo.mydevices.presentation.utils.getDeviceIcon
import javax.inject.Inject

class DevicesUiMapper @Inject constructor() {
    fun map(
        devices: List<Device>
    ): List<DeviceUiModel> {
        val resultList = mutableListOf<DeviceUiModel>()
        devices.forEach {
            resultList.add(
                DeviceUiModel(
                    title = it.header,
                    deviceSn = it.pkDevice,
                    deviceIcon = getDeviceIcon(it.platform)
                )
            )
        }
        return resultList
    }
}