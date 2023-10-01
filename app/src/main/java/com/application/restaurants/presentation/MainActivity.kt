package com.application.restaurants.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.application.restaurants.utils.Constants.RESTAURANTS
import com.application.restaurants.utils.Constants.URI_PATTERN
import com.application.restaurants.presentation.details.RestaurantDetailsScreen
import com.application.restaurants.presentation.details.RestaurantDetailsViewModel
import com.application.restaurants.presentation.list.RestaurantsScreen
import com.application.restaurants.presentation.list.RestaurantsViewModel
import com.application.restaurants.ui.theme.RestaurantsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantsTheme {
                RestaurantsApp()
            }
        }
    }
}

@Composable
fun RestaurantsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RESTAURANTS) {

        composable(
            route = RESTAURANTS,
        ) {
            val viewModel: RestaurantsViewModel = viewModel()
            RestaurantsScreen(
                state = viewModel.state.value,
                onItemClick = { id -> navController.navigate("$RESTAURANTS/$id") }
            ) { id, oldValue ->
                viewModel.toggleFavorite(id, oldValue)
            }
        }

        composable(
            route = "$RESTAURANTS/{restaurant_id}",

            arguments = listOf(navArgument("restaurant_id") {
                type = NavType.IntType
            }),

            deepLinks = listOf(navDeepLink {
                uriPattern = URI_PATTERN
            })

        ) {
            val viewModel: RestaurantDetailsViewModel = viewModel()
            RestaurantDetailsScreen(
                item = viewModel.state.value.restaurant
            )
        }
    }
}
