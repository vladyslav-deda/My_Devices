package com.ezlo.mydevices.data.network

import com.ezlo.mydevices.data.models.DevicesResponse
import retrofit2.Call
import retrofit2.http.GET

interface DevicesService {

    @GET("items.test")
    fun getDevicesList(): Call<DevicesResponse>
}