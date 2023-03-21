package com.example.starwars.presentation.model

import com.example.starwars.data.model.MoviesRemote
import com.example.starwars.data.model.PeopleRemote
import com.example.starwars.presentation.ext.prepareUrl
import java.io.Serializable

data class Movie(
    val image: String,
    val title: String,
    val launchDate: String,
    val director: String
) : Serializable, Item(title)

fun List<MoviesRemote>.toMovie(): List<Movie> {
    val movieList = mutableListOf<Movie>()
    this.forEach {
        movieList.add(
            Movie(
                image = it.image.prepareUrl("films"),
                title = it.title,
                launchDate = it.launchDate,
                director = it.director
            )
        )
    }
    return movieList
}