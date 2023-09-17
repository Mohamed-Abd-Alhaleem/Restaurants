package com.application.restaurants

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.application.restaurants.Constants.BASE_URL
import com.application.restaurants.Constants.KEY_FAVORITES
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    private var restInterface: RestaurantsApiService
    val state = mutableStateOf(emptyList<Restaurant>())
    private lateinit var restaurantsCall : Call<List<Restaurant>>

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        restInterface = retrofit.create(RestaurantsApiService::class.java)

        getRestaurants()
    }

    fun toggleFavorite(id: Int) {
        val restaurants = state.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        storeSelection(restaurants[itemIndex])
        state.value = restaurants
    }

    private fun storeSelection(item: Restaurant) {
        val savedToggled = stateHandle
            .get<List<Int>?>(KEY_FAVORITES)
            .orEmpty()
            .toMutableList()
        if (item.isFavorite)
            savedToggled.add(item.id)
        else
            savedToggled.remove(item.id)
        stateHandle[KEY_FAVORITES] = savedToggled
    }

    private fun List<Restaurant>.restoreSelections(): List<Restaurant> {
        stateHandle
            .get<List<Int>?>(KEY_FAVORITES)?.let { selectedIds ->
                val restaurantsMap = this.associateBy { it.id }
                selectedIds.forEach { id ->
                    restaurantsMap[id]?.isFavorite = true
                }
                return restaurantsMap.values.toList()
            }
        return this
    }

    private fun getRestaurants() {
        restaurantsCall = restInterface.getRestaurants()
        restaurantsCall.enqueue(
            object : Callback<List<Restaurant>> {
                override fun onResponse(
                    call: Call<List<Restaurant>>,
                    response: Response<List<Restaurant>>
                ) {
                    response.body()?.let { restaurants ->
                        state.value = restaurants.restoreSelections()
                    }
                }

                override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                    t.printStackTrace()
                }

            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        restaurantsCall.cancel()
    }

}