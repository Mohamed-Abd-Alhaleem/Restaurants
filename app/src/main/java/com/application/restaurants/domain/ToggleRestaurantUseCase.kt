package com.application.restaurants.domain

import com.application.restaurants.data.RestaurantsRepository
import com.application.restaurants.domain.GetSortedRestaurantsUseCase
import com.application.restaurants.domain.Restaurant
import javax.inject.Inject

class ToggleRestaurantUseCase @Inject constructor(
    private val repository: RestaurantsRepository,
    private val getSortedRestaurantsUseCase: GetSortedRestaurantsUseCase
) {
    suspend operator fun invoke(id: Int, oldValue: Boolean): List<Restaurant> {
        val newFav = oldValue.not()
        repository.toggleFavoriteRestaurant(id, newFav)
        return getSortedRestaurantsUseCase()
    }

}