package com.example.starwars.data

import com.google.gson.annotations.SerializedName

data class MoviesPage(
    @SerializedName("results")
    val movieList:List<MoviesPage>
)