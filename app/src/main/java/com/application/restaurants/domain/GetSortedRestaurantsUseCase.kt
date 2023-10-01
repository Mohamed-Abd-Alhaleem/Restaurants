package com.application.restaurants.domain

import com.application.restaurants.data.RestaurantsRepository

class GetSortedRestaurantsUseCase {

    private val repository = RestaurantsRepository()

    suspend operator fun invoke(): List<Restaurant> {
        return repository.getRestaurants().sortedBy { it.title }
    }

}