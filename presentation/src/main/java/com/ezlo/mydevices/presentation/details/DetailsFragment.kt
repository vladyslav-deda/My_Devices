package com.ezlo.mydevices.presentation.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.effects.collect(::handleEffects)
            }
        }
        binding.deviceTitleEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.handleTitleInput(text.toString())
        }
        binding.applyChangesBtn.setOnClickListener {
            viewModel.onApplyChangesClicked()
        }
    }

    private fun handleEffects(effect: DetailsContract.Effect) {
        when (effect) {
            is DetailsContract.Effect.SetupTitle -> handleEditMode(
                effect.isEditMode,
                effect.defaultTitle
            )

            DetailsContract.Effect.ShowSuccessfulMessage -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.successfully_updated_data_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleEditMode(isEditMode: Boolean, defaultTitle: String) {
        if (isEditMode) {
            setupEditMode(defaultTitle)
        } else {
            setupViewMode(defaultTitle)
        }
    }

    private fun handleUiState(state: DetailsContract.State) {
        fragmentCallback.handleLoaderVisibility(state.isLoading)
        if (state.isLoading.not()) {
            setupDeviceImage(state.deviceIcon)
            setupDeviceInfo(
                deviceSn = state.deviceSn,
                deviceMacAddress = state.deviceMacAddress,
                deviceFirmware = state.deviceFirmware,
                deviceModelResource = state.deviceModelResource
            )
            binding.applyChangesBtn.isEnabled = state.isApplyButtonEnabled
        }
    }

    private fun setupDeviceImage(deviceIcon: Int?) {
        deviceIcon?.let { iconUrl ->
            Glide.with(requireContext())
                .load(iconUrl)
                .centerCrop()
                .into(binding.deviceImageIv)
        }
    }

    private fun setupEditMode(title: String) {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        binding.run {
            deviceTitleEditText.setText(title)
            deviceTitleInputLayout.visibility = View.VISIBLE
            applyChangesBtn.visibility = View.VISIBLE
            deviceTitleTv.visibility = View.GONE
            deviceTitleEditText.requestFocus()
            inputMethodManager?.showSoftInput(deviceTitleEditText, InputMethodManager.SHOW_IMPLICIT)
        }

    }

    private fun setupViewMode(title: String) {
        binding.run {
            deviceTitleTv.apply {
                text = title
                visibility = View.VISIBLE
            }
            applyChangesBtn.visibility = View.GONE
            deviceTitleInputLayout.visibility = View.GONE
        }
    }

    private fun setupDeviceInfo(
        deviceSn: Int?,
        deviceMacAddress: String?,
        deviceFirmware: String?,
        deviceModelResource: Int?
    ) {
        binding.run {
            deviceSnTv.text = getString(R.string.device_sn, deviceSn)
            deviceMacAddressTv.text = getString(R.string.device_mac_address, deviceMacAddress)
            deviceFirmwareTv.text = getString(R.string.device_firmware, deviceFirmware)
            deviceModelResource?.let { modelResource ->
                deviceModelTv.text = getString(R.string.device_model, getString(modelResource))
            }
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}