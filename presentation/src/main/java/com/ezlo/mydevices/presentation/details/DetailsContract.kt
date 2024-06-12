package com.ezlo.mydevices.presentation.details

interface DetailsContract {
    data class State(
        val isLoading: Boolean = false,
        val deviceIcon: Int? = null,
        val title: String? = null,
        val deviceSn: Int? = null,
        val deviceMacAddress: String? = null,
        val deviceFirmware: String? = null,
        val deviceModelResource: Int? = null,
        val isEditMode: Boolean = false,
        val isApplyButtonEnabled: Boolean = false,
    )

    sealed interface Effect {
        data class SetupTitle(val isEditMode: Boolean, val defaultTitle: String) : Effect

        data object ShowSuccessfulMessage : Effect
    }
}