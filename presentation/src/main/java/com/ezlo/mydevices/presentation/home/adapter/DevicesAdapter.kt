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

    override fun onBindViewHolder(
        holder: DeviceViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        when (val latestPayload = payloads.lastOrNull()) {
            is DevicePayload -> {
                latestPayload.title?.let {
                    holder.bindTitle(it)
                }
            }

            else -> onBindViewHolder(holder, position)
        }
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

    override fun getChangePayload(
        oldItem: DeviceUiModel,
        newItem: DeviceUiModel,
    ): Any? {
        val title = if (oldItem.title != newItem.title) newItem.title else null
        return when {
            title != null -> DevicePayload(title = title)

            else -> super.getChangePayload(oldItem, newItem)
        }
    }
}