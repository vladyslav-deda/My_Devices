package com.ezlo.mydevices.presentation.home

interface HomeContract {
    data class State(
        val isLoading: Boolean = false,
    )

    sealed interface Effect {}
}