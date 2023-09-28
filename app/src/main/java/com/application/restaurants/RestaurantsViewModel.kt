package com.application.restaurants

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class RestaurantsViewModel(private val repository: RestaurantsRepository) : ViewModel() {

    private val _state = mutableStateOf(
        RestaurantsScreenState(
            restaurants = listOf(),
            isLoading = true
        )
    )

    val state: State<RestaurantsScreenState>
        get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

    init {
        getRestaurants()
    }

    fun toggleFavorite(id: Int, oldValue: Boolean) {
        viewModelScope.launch(errorHandler) {
            val updatedRestaurants = repository.toggleFavoriteRestaurant(id, oldValue)
            _state.value = _state.value.copy(
                restaurants = updatedRestaurants
            )
        }
    }

    private fun getRestaurants() {
        viewModelScope.launch(errorHandler) {
            val restaurants = repository.getAllRestaurants()
            _state.value = _state.value.copy(
                restaurants = restaurants,
                isLoading = false
            )
        }
    }

}

class ViewModelFactory(private val repository: RestaurantsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantsViewModel::class.java))
            return RestaurantsViewModel(repository) as T
        else
            throw IllegalArgumentException("View Model Class Not Found")
    }
}