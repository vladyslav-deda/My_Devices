package com.ezlo.mydevices.presentation.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.ezlo.mydevices.presentation.FragmentCallback
import com.ezlo.mydevices.presentation.R
import com.ezlo.mydevices.presentation.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding: DetailsFragmentBinding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var fragmentCallback: FragmentCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentCallback = context as FragmentCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect(::handleUiState)
            }
        }

    }

    private fun handleUiState(state: DetailsContract.State) {
        fragmentCallback.handleLoaderVisibility(state.isLoading)
        if (state.isLoading.not()) {
            binding.run {
                Glide
                    .with(requireContext())
                    .load(state.deviceIcon)
                    .centerCrop()
                    .into(deviceImageIv)
                deviceTitleTv.text = state.title
                deviceSnTv.text =
                    requireContext().resources.getString(R.string.device_sn, state.deviceSn)
                deviceMacAddressTv.text =
                    requireContext().resources.getString(
                        R.string.device_mac_address,
                        state.deviceMacAddress
                    )
                deviceFirmwareTv.text =
                    requireContext().resources.getString(
                        R.string.device_firmware,
                        state.deviceFirmware
                    )
                val deviceModel = state.deviceModelResource?.let {
                    requireContext().resources.getString(it)
                }
                deviceModelTv.text =
                    requireContext().resources.getString(R.string.device_model, deviceModel)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}