package com.application.restaurants.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.application.restaurants.domain.Restaurant

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM restaurants")
    suspend fun getAll(): List<LocalRestaurant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(restaurants: List<LocalRestaurant>)

    @Update(entity = LocalRestaurant::class)
    suspend fun update(partialLocalRestaurant: PartialLocalRestaurant)

    @Update(entity = LocalRestaurant::class)
    suspend fun updateAll(partialLocalRestaurants: List<PartialLocalRestaurant>)

    @Query("SELECT * FROM restaurants WHERE is_favorite = 1")
    suspend fun getAllFavorited(): List<LocalRestaurant>

}