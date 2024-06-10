package com.ezlo.mydevices.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
                true
            }
            deviceTitleTv.text = item.title
            deviceSnTv.text = item.deviceSn
            Glide
                .with(root.context)
                .load(androidx.constraintlayout.widget.R.drawable.tooltip_frame_dark)
                .transform(RoundedCorners(IMAGE_CORNER))
                .into(binding.deviceImageIv)

        }
    }

    companion object {
        private const val IMAGE_CORNER = 30
    }
}