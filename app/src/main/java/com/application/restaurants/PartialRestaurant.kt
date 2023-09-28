package com.application.restaurants

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PartialRestaurant(

    @ColumnInfo(name = "r_id")
    val id: Int,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean

)
