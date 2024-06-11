package com.ezlo.mydevices.utils

import android.content.Context
import android.view.LayoutInflater
import com.ezlo.mydevices.R
import com.ezlo.mydevices.databinding.DeleteDeviceDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showDeleteDeviceDialog(
    deviceSn: Int,
    onDeleteButtonClick: () -> Unit,
) {
    val dialog = MaterialAlertDialogBuilder(this).create()

    val binding = DeleteDeviceDialogBinding.inflate(LayoutInflater.from(this))
    binding.run {
        messageTv.text = this@showDeleteDeviceDialog.getString(R.string.delete_message, deviceSn)
        deleteBtn.setOnClickListener {
            onDeleteButtonClick.invoke()
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

    dialog.run {
        setView(binding.root)
        show()
    }
}