package com.ezlo.mydevices.presentation.home

import com.ezlo.mydevices.presentation.home.models.DeviceUiModel

interface HomeContract {
    data class State(
        val isLoading: Boolean = false,
        val items: List<DeviceUiModel> = emptyList(),
    )

    sealed interface Effect {
        data class ShowError(val throwable: Throwable) : Effect

        data class OpenDeviceDetails(val deviceSn: Int, val isEditMode: Boolean) : Effect

        data class OpenDeleteDeviceDialog(val deviceSn: Int) : Effect
    }
}