package com.ezlo.mydevices.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.ezlo.mydevices.R
import com.ezlo.mydevices.databinding.ActivityMainBinding
import com.ezlo.mydevices.presentation.FragmentCallback
import com.ezlo.mydevices.utils.showDeleteDeviceDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentCallback {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide
            .with(this)
            .load(R.drawable.profile_photo)
            .circleCrop()
            .into(binding.profilePhotoIv)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect(::handleUiState)
            }
        }
    }

    private fun handleUiState(state: MainContract.State) {
        handleLoaderVisibility(state.isLoading)
    }

    override fun handleLoaderVisibility(isLoaderVisible: Boolean) {
        binding.loaderLayout.isVisible = isLoaderVisible
    }

    override fun handleDeleteDevice(deviceSn: Long) {
        this.showDeleteDeviceDialog(deviceSn) {
            viewModel.handleDeleteClicked(deviceSn)
        }
    }
}