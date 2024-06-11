package com.ezlo.mydevices.presentation.home.models

import androidx.annotation.DrawableRes

data class DeviceUiModel(
    val title: String,
    val deviceSn: Int,
    @DrawableRes val deviceIcon: Int,
)