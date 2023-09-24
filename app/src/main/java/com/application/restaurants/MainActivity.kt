package com.application.restaurants

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.application.restaurants.Constants.RESTAURANTS
import com.application.restaurants.Constants.URI_PATTERN
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
            RestaurantsScreen { id ->
                navController.navigate("$RESTAURANTS/$id")
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
            RestaurantDetailsScreen()
        }
    }
}

