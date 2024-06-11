package com.ezlo.mydevices.activity

interface MainContract {
    data class State(
        val isLoading: Boolean = false,
    )
}