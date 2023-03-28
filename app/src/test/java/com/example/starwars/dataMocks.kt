package com.example.starwars

import com.example.starwars.data.model.MoviesPage
import com.example.starwars.data.model.MoviesRemote
import com.example.starwars.data.model.PeoplePage
import com.example.starwars.data.model.PeopleRemote
import com.example.starwars.presentation.model.Movie
import com.example.starwars.presentation.model.People
import com.example.starwars.presentation.model.toMovieList

val peopleRemoteList = listOf(
    PeopleRemote(
        "https://swapi.dev/api/people/1/",
        "Luke Skywalker",
        "172",
        "77",
        "19BBY",
        "blue"
    )
)

val pagePeople = PeoplePage(
    null,
    null,
    peopleRemoteList
)
val people = People(
    image = "https://starwars-visualguide.com/assets/img/characters/1.jpg",
    name = "Luke Skywalker",
    height = "172cm",
    mass = "77 Kg",
    eyesColor = "blue",
    birthYear = "19BBY"
)

val movieRemoteList = listOf(
    MoviesRemote(
        "https://swapi.dev/api/films/1/",
        "A New Hope",
        "25/05/1977",
        "George Lucas",
        "Gary Kurtz, Rick McCallum",
        "It is a period of civil war.\\r\\nRebel spaceships, striking\\r\\nfrom a hidden base, have won\\r\\ntheir first victory against\\r\\nthe evil Galactic Empire.\\r\\n\\r\\nDuring the battle, Rebel\\r\\nspies managed to steal secret\\r\\nplans to the Empire's\\r\\nultimate weapon, the DEATH\\r\\nSTAR, an armored space\\r\\nstation with enough power\\r\\nto destroy an entire planet.\\r\\n\\r\\nPursued by the Empire's\\r\\nsinister agents, Princess\\r\\nLeia races home aboard her\\r\\nstarship, custodian of the\\r\\nstolen plans that can save her\\r\\npeople and restore\\r\\nfreedom to the galaxy...."
    )
)
val moviePage = MoviesPage(null, null, movieRemoteList)

val movie = movieRemoteList.toMovieList()[0]