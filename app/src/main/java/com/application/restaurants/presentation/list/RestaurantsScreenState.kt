package com.application.restaurants.presentation.list

import com.application.restaurants.domain.Restaurant

data class RestaurantsScreenState(
    val restaurants: List<Restaurant>,
    val isLoading: Boolean,
    val error: String? = null
)
