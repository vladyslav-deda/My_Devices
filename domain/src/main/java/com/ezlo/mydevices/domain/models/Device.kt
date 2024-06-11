package com.ezlo.mydevices.domain.models

data class Device(
    val pkDevice: Int,
    val macAddress: String,
    val pkDeviceType: Int,
    val pDeviceSubType: Int,
    val firmware: String,
    val serverDevice: String,
    val serverEvent: String,
    val serverAccount: String,
    val internalIP: String,
    val lastAliveReported: String,
    val platform: String,
    var header: String,
)