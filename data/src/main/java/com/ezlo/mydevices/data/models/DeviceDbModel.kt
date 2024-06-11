package com.ezlo.mydevices.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "devices")
data class DeviceDbModel(
    @PrimaryKey
    val pkDevice: Long,
    val header: String,
    val macAddress: String,
    val pkDeviceType: Int,
    val pDeviceSubType: Int,
    val firmware: String,
    val serverDevice: String,
    val serverEvent: String,
    val serverAccount: String,
    val internalIP: String,
    val lastAliveReported: String,
    val platform: String
)