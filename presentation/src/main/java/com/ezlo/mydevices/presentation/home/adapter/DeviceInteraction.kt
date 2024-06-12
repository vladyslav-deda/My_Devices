package com.ezlo.mydevices.presentation.home.adapter

interface DeviceInteraction {
    fun openDetails(deviceSn: Int, isEditMode: Boolean = false)

    fun handleDeleteDevice(deviceSn: Int)
}