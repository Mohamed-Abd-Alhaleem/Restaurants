package com.application.restaurants

import com.application.restaurants.Constants.RESTAURANTS_JSON
import com.application.restaurants.Constants.RESTAURANTS_JSON_ORDER_BY_ID
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApiService {

    @GET(RESTAURANTS_JSON)
    suspend fun getRestaurants() : List<Restaurant>

    @GET(RESTAURANTS_JSON_ORDER_BY_ID)
    suspend fun getRestaurants(@Query("equalTo") id: Int) : Map<String, Restaurant>

}