package com.ezlo.mydevices.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ezlo.mydevices.presentation.R
import com.ezlo.mydevices.presentation.databinding.HomeDeviceItemBinding
import com.ezlo.mydevices.presentation.home.models.DeviceUiModel

class DeviceViewHolder(
    private val binding: HomeDeviceItemBinding,
    private val interaction: DeviceInteraction,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DeviceUiModel) {
        binding.run {
            root.setOnClickListener {
                interaction.openDetails(item.deviceSn)
            }
            root.setOnLongClickListener {
                interaction.handleDeleteDevice(item.deviceSn)
                true
            }
            editBtn.setOnClickListener {
                interaction.openDetails(deviceSn = item.deviceSn, isEditMode = true)
            }
            deviceTitleTv.text = item.title
            deviceSnTv.text = root.resources.getString(R.string.device_sn, item.deviceSn)
            Glide
                .with(root.context)
                .load(item.deviceIcon)
                .centerCrop()
                .into(deviceImageIv)

        }
    }
}