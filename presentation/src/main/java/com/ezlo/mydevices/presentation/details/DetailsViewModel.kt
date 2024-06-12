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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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

    private val _effects = Channel<DetailsContract.Effect>()
    val effects = _effects.receiveAsFlow()

    private var device: Device? = null
    private var userTitleInput: String = ""

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                device = localDevicesRepository.getDevicesById(deviceId)
                _effects.send(
                    DetailsContract.Effect.SetupTitle(
                        isEditMode = isEditMode,
                        defaultTitle = device?.header ?: ""
                    )
                )
            } catch (throwable: Throwable) {
                Timber.d(throwable)
            } finally {
                updateState()
            }
        }
    }

    fun onApplyChangesClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateState(isLoading = true)
                val newDevice = device?.copy(header = userTitleInput.trim())
                newDevice?.let {
                    localDevicesRepository.insertDevice(it)
                }
                device = newDevice
                userTitleInput = ""
                _effects.send(DetailsContract.Effect.ShowSuccessfulMessage)
            } catch (throwable: Throwable) {
                Timber.d(throwable)
            } finally {
                updateState()
            }
        }
    }

    fun handleTitleInput(text: String) {
        userTitleInput = text
        updateState()
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
                isApplyButtonEnabled = isApplyButtonEnabled()
            )
        }
    }

    private fun isApplyButtonEnabled() =
        userTitleInput != device?.header && userTitleInput.isNotEmpty()
}