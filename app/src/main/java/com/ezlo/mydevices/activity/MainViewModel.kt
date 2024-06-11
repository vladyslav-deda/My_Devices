package com.ezlo.mydevices.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlo.mydevices.domain.repositories.LocalDevicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localDevicesRepository: LocalDevicesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainContract.State())
    val uiState: StateFlow<MainContract.State> = _uiState.asStateFlow()

    fun handleDeleteClicked(deviceSn: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                localDevicesRepository.deleteDeviceById(deviceSn)
            } catch (throwable: Throwable) {
                Timber.d(throwable)
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
}