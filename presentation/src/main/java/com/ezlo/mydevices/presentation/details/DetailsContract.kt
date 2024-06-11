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
    )
}