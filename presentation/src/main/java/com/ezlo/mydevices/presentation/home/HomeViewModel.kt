package com.ezlo.mydevices.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlo.mydevices.domain.InitDevicesListUseCase
import com.ezlo.mydevices.domain.models.Device
import com.ezlo.mydevices.domain.repositories.LocalDevicesRepository
import com.ezlo.mydevices.presentation.home.adapter.DeviceInteraction
import com.ezlo.mydevices.presentation.home.mapper.DevicesUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val devicesUiMapper: DevicesUiMapper,
    private val initDevicesListUseCase: InitDevicesListUseCase,
    private val localDevicesRepository: LocalDevicesRepository
) : ViewModel(), DeviceInteraction {
    private val _uiState = MutableStateFlow(HomeContract.State(isLoading = true))
    val uiState: StateFlow<HomeContract.State> = _uiState.asStateFlow()

    private val _effects = Channel<HomeContract.Effect>()
    val effects = _effects.receiveAsFlow()

    private var devicesList: List<Device>? = null

    init {
        viewModelScope.launch {
            try {
                initDevicesListUseCase()
                localDevicesRepository.allDevices.collectLatest {
                    devicesList = it
                    updateUiState()
                }
            } catch (throwable: Throwable) {
                _effects.send(HomeContract.Effect.ShowError(throwable))
            } finally {
                updateUiState()
            }
        }
    }

    override fun openDetails(deviceSn: Int) {

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