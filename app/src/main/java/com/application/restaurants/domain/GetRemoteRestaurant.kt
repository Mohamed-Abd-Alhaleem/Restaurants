package com.application.restaurants.domain

import com.application.restaurants.data.RestaurantsRepository
import javax.inject.Inject

class GetRemoteRestaurant @Inject constructor(
    private val repository: RestaurantsRepository
) {

    suspend operator fun invoke(id: Int): Restaurant {
        return repository.getRemoteRestaurant(id)
    }

}