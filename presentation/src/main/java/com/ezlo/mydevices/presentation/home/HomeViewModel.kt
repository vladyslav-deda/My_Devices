package com.ezlo.mydevices.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlo.mydevices.domain.models.Device
import com.ezlo.mydevices.domain.repositories.DevicesRepository
import com.ezlo.mydevices.presentation.home.adapter.DeviceInteraction
import com.ezlo.mydevices.presentation.home.mapper.DevicesUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val devicesRepository: DevicesRepository,
    private val devicesUiMapper: DevicesUiMapper,
) : ViewModel(), DeviceInteraction {
    private val _uiState = MutableStateFlow(HomeContract.State(isLoading = true))
    val uiState: StateFlow<HomeContract.State> = _uiState.asStateFlow()

    private val _effects = Channel<HomeContract.Effect>()
    val effects = _effects.receiveAsFlow()

    private var devicesList: List<Device>? = null

    init {
        viewModelScope.launch {
            try {
                devicesList = devicesRepository.retrieveDevicesList()
            } catch (throwable: Throwable) {
                _effects.send(HomeContract.Effect.ShowError(throwable))
            } finally {
                updateUiState()
            }
        }
    }

    override fun openDetails(deviceSn: String) {

    }

    private fun updateUiState(
        isLoading: Boolean = false
    ) {
        _uiState.update {
            it.copy(
                isLoading = isLoading,
                items = devicesUiMapper.map(devicesList ?: emptyList())
            )
        }
    }
}