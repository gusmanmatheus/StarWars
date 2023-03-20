package com.example.starwars.data.model

import com.google.gson.annotations.SerializedName

data class MoviesPage(
    @SerializedName("results")
    val movieList:List<MoviesRemote>
)