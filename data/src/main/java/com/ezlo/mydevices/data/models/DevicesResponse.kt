package com.ezlo.mydevices.data.models

import com.google.gson.annotations.SerializedName

data class DevicesResponse(
    @SerializedName("Devices") val devices: ArrayList<DeviceDto>? = null
)

data class DeviceDto(
    @SerializedName("PK_Device") val pkDevice: Int? = null,
    @SerializedName("MacAddress") val macAddress: String? = null,
    @SerializedName("PK_DeviceType") val pkDeviceType: Int? = null,
    @SerializedName("PK_DeviceSubType") val pDeviceSubType: Int? = null,
    @SerializedName("Firmware") val firmware: String? = null,
    @SerializedName("Server_Device") val serverDevice: String? = null,
    @SerializedName("Server_Event") val serverEvent: String? = null,
    @SerializedName("Server_Account") val serverAccount: String? = null,
    @SerializedName("InternalIP") val internalIP: String? = null,
    @SerializedName("LastAliveReported") val lastAliveReported: String? = null,
    @SerializedName("Platform") val platform: String? = null
)