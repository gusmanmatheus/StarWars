package com.example.starwars.presentation.model

import com.example.starwars.data.MoviesRemote
import java.io.Serializable

data class Movie(
    val image: String,
    val title: String,
    val launchDate: String,
    val director: String
): Serializable

fun MoviesRemote.toMovie()= Movie(
    image = this.image,
    title = this.title,
    launchDate = this.launchDate,
    director = this.director
)