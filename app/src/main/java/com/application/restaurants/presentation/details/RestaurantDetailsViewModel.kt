package com.application.restaurants.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.restaurants.utils.Constants
import com.application.restaurants.domain.Restaurant
import com.application.restaurants.data.remote.RestaurantsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantDetailsViewModel(stateHandle: SavedStateHandle) : ViewModel() {

    private var restInterface: RestaurantsApiService

    private val _state = mutableStateOf<RestaurantsDetailsScreenState>(
        RestaurantsDetailsScreenState(
            restaurant = null
        )
    )
    val state: State<RestaurantsDetailsScreenState>
        get() = _state

    init {

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()

        restInterface = retrofit.create(RestaurantsApiService::class.java)

        val id = stateHandle.get<Int>("restaurant_id") ?: 0

        viewModelScope.launch {
            val restaurant = getRemoteRestaurant(id)
            _state.value = RestaurantsDetailsScreenState(
                restaurant = restaurant
            )
        }
    }

    private suspend fun getRemoteRestaurant(id: Int): Restaurant {
        return withContext(Dispatchers.IO) {
            val responseMap = restInterface.getRestaurants(id)
            return@withContext responseMap.values.first().let {
                Restaurant(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                )
            }
        }
    }

}
