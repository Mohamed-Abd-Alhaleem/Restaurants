package com.application.restaurants.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.application.restaurants.domain.Restaurant

@Composable
fun RestaurantsScreen(
    state: RestaurantsScreenState,
    onItemClick: (id: Int) -> Unit,
    onFavoriteClick: (id: Int, oldValue: Boolean) -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 8.dp,
                horizontal = 8.dp
            )
        ) {
            items(state.restaurants) { restaurant ->
                RestaurantItem(item = restaurant, { id, oldValue ->
                    onFavoriteClick(id, oldValue)
                }) { id ->
                    onItemClick(id)
                }
            }
        }

        if (state.isLoading)
            CircularProgressIndicator()

        if (state.error != null)
            Text(text = state.error)

    }
}

@Composable
fun RestaurantItem(
    item: Restaurant,
    onFavoriteClick: (id: Int, oldValue: Boolean) -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    val icon = if (item.isFavorite)
        Icons.Filled.Favorite
    else
        Icons.Filled.FavoriteBorder
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClick(item.id)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            RestaurantIcon(
                Icons.Filled.Place,
                Modifier.weight(0.15f)
            )
            RestaurantDetails(
                item.title,
                item.description,
                Modifier.weight(0.85f)
            )
            RestaurantIcon(
                icon,
                Modifier.weight(0.15f)
            ) {
                onFavoriteClick(item.id, item.isFavorite)
            }
        }
    }
}

@Composable
fun RestaurantDetails(
    item: String,
    description: String,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.h6
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun RestaurantIcon(
    icon: ImageVector,
    modifier: Modifier,
    onClick: () -> Unit = {}
) {
    Image(
        imageVector = icon,
        contentDescription = "Restaurant icon",
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onClick()
            }
    )
}
