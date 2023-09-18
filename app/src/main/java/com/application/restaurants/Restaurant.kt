package com.application.restaurants

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName(value = "r_id")
    val id: Int,
    @SerializedName(value = "r_title")
    val title: String,
    @SerializedName(value = "r_description")
    val description: String,
    var isFavorite: Boolean = false
)