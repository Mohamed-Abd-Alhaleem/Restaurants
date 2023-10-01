package com.application.restaurants.data.remote

import com.application.restaurants.utils.Constants.RESTAURANTS_JSON
import com.application.restaurants.utils.Constants.RESTAURANTS_JSON_ORDER_BY_ID
import com.application.restaurants.domain.Restaurant
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantsApiService {

    @GET(RESTAURANTS_JSON)
    suspend fun getRestaurants() : List<RemoteRestaurant>

    @GET(RESTAURANTS_JSON_ORDER_BY_ID)
    suspend fun getRestaurants(@Query("equalTo") id: Int) : Map<String, RemoteRestaurant>

}