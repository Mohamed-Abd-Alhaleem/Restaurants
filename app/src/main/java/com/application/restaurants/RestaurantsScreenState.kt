package com.application.restaurants

data class RestaurantsScreenState(
    val restaurants: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
)
