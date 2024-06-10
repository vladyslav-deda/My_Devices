package com.ezlo.mydevices.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.ezlo.mydevices.presentation.R
import com.ezlo.mydevices.presentation.databinding.HomeFragmentBinding
import com.ezlo.mydevices.presentation.home.adapter.DevicesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private var devicesAdapter: DevicesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        devicesAdapter = DevicesAdapter(viewModel)
        binding.devicesRecycler.apply {
            adapter = devicesAdapter
            addItemDecoration(getRecyclerViewVerticalDivider())
        }
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
    }

    private fun handleUiState(state: HomeContract.State) {
        devicesAdapter?.submitList(state.items)
    }

    private fun handleEffects(effect: HomeContract.Effect) {
        when (effect) {
            is HomeContract.Effect.ShowError -> {}
        }
    }

    private fun getRecyclerViewVerticalDivider(): DividerItemDecoration {
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val dividerDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.divider_black)
        dividerDrawable?.let {
            dividerItemDecoration.setDrawable(it)
        }
        return dividerItemDecoration
    }

    override fun onDestroyView() {
        _binding = null
        devicesAdapter = null
        super.onDestroyView()
    }
}