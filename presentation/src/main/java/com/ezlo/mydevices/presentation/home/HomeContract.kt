package com.ezlo.mydevices.presentation.home

import com.ezlo.mydevices.presentation.home.models.DeviceUiModel

interface HomeContract {
    data class State(
        val isLoading: Boolean = false,
        val items: List<DeviceUiModel> = emptyList(),
    )

    sealed interface Effect {
        data class ShowError(val throwable: Throwable) : Effect
    }
}