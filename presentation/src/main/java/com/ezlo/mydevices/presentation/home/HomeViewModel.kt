package com.ezlo.mydevices.presentation.home

import androidx.lifecycle.ViewModel
import com.ezlo.mydevices.presentation.home.adapter.DeviceInteraction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel(), DeviceInteraction {

    override fun openDetails(deviceSn: String) {

    }
}