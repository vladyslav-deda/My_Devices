package com.ezlo.mydevices.data.utils

import com.ezlo.mydevices.data.models.DeviceDbModel
import com.ezlo.mydevices.data.models.DeviceDto
import com.ezlo.mydevices.domain.models.Device
import javax.inject.Inject

object DevicesMappers {
    fun mapDeviceDtoToDomain(list: List<DeviceDto>): List<Device> {
        return list.map {
            it.toDomain()
        }
    }

    private fun DeviceDto.toDomain() =
        this.run {
            Device(
                header = "",
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

    fun mapDeviceDbModelToDomain(list: List<DeviceDbModel>): List<Device> {
        return list.map {
            it.toDomain()
        }
    }

    private fun DeviceDbModel.toDomain() =
        this.run {
            Device(
                header = header,
                pkDevice = pkDevice,
                macAddress = macAddress,
                pkDeviceType = pkDeviceType,
                pDeviceSubType = pDeviceSubType,
                firmware = firmware,
                serverDevice = serverDevice,
                serverEvent = serverEvent,
                serverAccount = serverAccount,
                internalIP = internalIP,
                lastAliveReported = lastAliveReported,
                platform = platform
            )
        }

    fun mapDeviceToDbModel(device: Device): DeviceDbModel {
        device.run {
            return DeviceDbModel(
                header = header,
                pkDevice = pkDevice,
                macAddress = macAddress,
                pkDeviceType = pkDeviceType,
                pDeviceSubType = pDeviceSubType,
                firmware = firmware,
                serverDevice = serverDevice,
                serverEvent = serverEvent,
                serverAccount = serverAccount,
                internalIP = internalIP,
                lastAliveReported = lastAliveReported,
                platform = platform
            )
        }
    }
}
