package com.ezlo.mydevices.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ezlo.mydevices.domain.repositories.LocalDevicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val localDevicesRepository: LocalDevicesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val deviceId: Int = savedStateHandle["deviceId"]!!
}