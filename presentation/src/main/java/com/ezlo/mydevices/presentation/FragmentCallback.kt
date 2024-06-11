package com.ezlo.mydevices.presentation

interface FragmentCallback {
    fun handleLoaderVisibility(isLoaderVisible: Boolean)

    fun handleDeleteDevice(deviceSn: Long)
}