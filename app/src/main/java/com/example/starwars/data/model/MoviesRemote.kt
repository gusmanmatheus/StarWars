package com.example.starwars.data.model

import com.google.gson.annotations.SerializedName

data class MoviesRemote(
    @SerializedName("url")
    val image:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("release_date")
    val launchDate:String,
    @SerializedName("director")
    val director:String
)