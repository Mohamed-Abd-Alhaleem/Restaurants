package com.application.restaurants.domain

import com.application.restaurants.data.RestaurantsRepository

class GetInitialRestaurantsUseCase {

    private val repository: RestaurantsRepository = RestaurantsRepository()
    private val getSortedRestaurantsUseCase = GetSortedRestaurantsUseCase()

    suspend operator fun invoke(): List<Restaurant> {
        repository.loadRestaurants()
        return getSortedRestaurantsUseCase()
    }

}