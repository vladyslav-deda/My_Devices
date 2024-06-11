package com.ezlo.mydevices.presentation.home.adapter

interface DeviceInteraction {
    fun openDetails(deviceSn: Long)

    fun handleDeleteDevice(deviceSn: Long)
}