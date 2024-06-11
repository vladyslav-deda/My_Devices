package com.ezlo.mydevices.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlo.mydevices.domain.models.Device
import com.ezlo.mydevices.domain.repositories.LocalDevicesRepository
import com.ezlo.mydevices.presentation.utils.getDeviceIcon
import com.ezlo.mydevices.presentation.utils.getDeviceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val localDevicesRepository: LocalDevicesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val deviceId: Int = savedStateHandle["deviceId"]!!
    private val isEditMode: Boolean = savedStateHandle["isEditMode"]!!

    private val _uiState = MutableStateFlow(DetailsContract.State())
    val uiState: StateFlow<DetailsContract.State> = _uiState.asStateFlow()

    private var device: Device? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                device = localDevicesRepository.getDevicesById(deviceId)
            } catch (throwable: Throwable) {
                Timber.d(throwable)
            } finally {
                updateState()
            }
        }
    }

    private fun updateState(
        isLoading: Boolean = false
    ) {
        _uiState.update {
            it.copy(
                isLoading = isLoading,
                deviceIcon = getDeviceIcon(device?.platform ?: ""),
                title = device?.header,
                deviceSn = device?.pkDevice,
                deviceMacAddress = device?.macAddress,
                deviceFirmware = device?.firmware,
                deviceModelResource = getDeviceModel(device?.platform ?: ""),
            )
        }
    }
}