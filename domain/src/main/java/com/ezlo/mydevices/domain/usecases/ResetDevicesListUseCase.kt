package com.ezlo.mydevices.domain.usecases

import android.content.Context
import com.ezlo.mydevices.domain.R
import com.ezlo.mydevices.domain.repositories.DevicesRepository
import com.ezlo.mydevices.domain.repositories.LocalDevicesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ResetDevicesListUseCase @Inject constructor(
    private val localDevicesRepository: LocalDevicesRepository,
    private val devicesRepository: DevicesRepository,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            val devices = devicesRepository.retrieveDevicesList()
            devices.forEachIndexed { index, device ->
                device.header = context.getString(R.string.house_number, index + 1)
            }
            localDevicesRepository.deleteAllDevices()
            localDevicesRepository.insertDevices(devices)
        }
    }
}