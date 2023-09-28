package com.application.restaurants

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel(
    private val stateHandle: SavedStateHandle,
    private val repository: RestaurantsRepository,
) : ViewModel() {

    private val _state = mutableStateOf<Restaurant?>(null)
    val state: State<Restaurant?>
        get() = _state

    init {
        val id = stateHandle.get<Int>("restaurant_id") ?: 0

        viewModelScope.launch {
            val restaurant = repository.getRemoteRestaurant(id)
            _state.value = restaurant
        }
    }

}

class DetailsViewModelFactory(
    private val repository: RestaurantsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return RestaurantDetailsViewModel(extras.createSavedStateHandle(), repository) as T
    }
}
