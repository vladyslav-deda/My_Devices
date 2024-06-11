package com.ezlo.mydevices.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.ezlo.mydevices.R
import com.ezlo.mydevices.databinding.ActivityMainBinding
import com.ezlo.mydevices.presentation.FragmentCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentCallback {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide
            .with(this)
            .load(R.drawable.profile_photo)
            .circleCrop()
            .into(binding.profilePhotoIv)
    }

    override fun handleLoaderVisibility(isLoaderVisible: Boolean) {
        binding.loaderLayout.isVisible = isLoaderVisible
    }
}