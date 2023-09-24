package com.application.restaurants

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurants")
data class Restaurant(

    @PrimaryKey()
    @ColumnInfo(name = "r_id")
    @SerializedName(value = "r_id")
    val id: Int,

    @ColumnInfo(name = "r_title")
    @SerializedName(value = "r_title")
    val title: String,

    @ColumnInfo(name = "r_description")
    @SerializedName(value = "r_description")
    val description: String,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)