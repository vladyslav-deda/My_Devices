package com.ezlo.mydevices.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ezlo.mydevices.presentation.databinding.HomeDeviceItemBinding
import com.ezlo.mydevices.presentation.home.models.DeviceUiModel

class DevicesAdapter(
    private val deviceInteraction: DeviceInteraction
) : ListAdapter<DeviceUiModel, DeviceViewHolder>(DevicesDiff) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceViewHolder {
        return DeviceViewHolder(
            HomeDeviceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            deviceInteraction
        )
    }

    override fun onBindViewHolder(
        holder: DeviceViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}

object DevicesDiff : DiffUtil.ItemCallback<DeviceUiModel>() {
    override fun areItemsTheSame(
        oldItem: DeviceUiModel,
        newItem: DeviceUiModel,
    ): Boolean {
        return oldItem.deviceSn == newItem.deviceSn
    }

    override fun areContentsTheSame(
        oldItem: DeviceUiModel,
        newItem: DeviceUiModel,
    ): Boolean {
        return oldItem == newItem
    }
}