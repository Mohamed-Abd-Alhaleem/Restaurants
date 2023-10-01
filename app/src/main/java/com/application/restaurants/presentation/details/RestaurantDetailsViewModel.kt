package com.application.restaurants.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.restaurants.domain.GetRemoteRestaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailsViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val getRemoteRestaurant: GetRemoteRestaurant
) : ViewModel() {

    private val _state = mutableStateOf(
        RestaurantsDetailsScreenState(
            restaurant = null
        )
    )
    val state: State<RestaurantsDetailsScreenState>
        get() = _state

    init {
        val id = stateHandle.get<Int>("restaurant_id") ?: 0

        viewModelScope.launch {
            val restaurant = getRemoteRestaurant(id)
            _state.value = RestaurantsDetailsScreenState(
                restaurant = restaurant
            )
        }
    }

}
