package com.ezlo.mydevices.presentation.utils

import com.ezlo.mydevices.presentation.R

private const val PLATFORM_SERCOMM_G_450 = "Sercomm G450"
private const val PLATFORM_SERCOMM_G_550 = "Sercomm G550"

fun getDeviceIcon(platform: String): Int {
    return when (platform) {
        PLATFORM_SERCOMM_G_450 -> R.drawable.vera_plus_big
        PLATFORM_SERCOMM_G_550 -> R.drawable.vera_secure_big
        else -> R.drawable.vera_edge_big
    }
}

fun getDeviceModel(platform: String): Int {
    return when (platform) {
        PLATFORM_SERCOMM_G_450 -> R.string.vera_plus
        PLATFORM_SERCOMM_G_550 -> R.string.vera_secure
        else -> R.string.vera_edge
    }
}