package com.application.restaurants

import com.application.restaurants.Constants.END_POINT_0
import retrofit2.Call
import retrofit2.http.GET

interface RestaurantsApiService {

    @GET(END_POINT_0)
    suspend fun getRestaurants() : List<Restaurant>

}