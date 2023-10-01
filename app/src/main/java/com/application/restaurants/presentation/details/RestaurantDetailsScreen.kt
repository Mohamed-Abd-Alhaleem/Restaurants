package com.application.restaurants.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.application.restaurants.domain.Restaurant
import com.application.restaurants.presentation.list.RestaurantDetails
import com.application.restaurants.presentation.list.RestaurantIcon

@Composable
fun RestaurantDetailsScreen(
    item: Restaurant?
) {

    if (item != null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            RestaurantIcon(
                icon = Icons.Filled.Place, modifier = Modifier.padding(
                    top = 32.dp,
                    bottom = 32.dp
                )
            )
            RestaurantDetails(
                item = item.title,
                description = item.description,
                modifier = Modifier.padding(bottom = 32.dp),
                Alignment.CenterHorizontally
            )
            Text(text = "More info coming soon!")
        }
    }
}
