package com.example.starwars

import com.example.starwars.data.model.PeoplePage
import com.example.starwars.data.model.PeopleRemote
import com.example.starwars.presentation.model.People

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
val people = People(image="https://starwars-visualguide.com/assets/img/characters/1.jpg", name="Luke Skywalker", height="172cm", mass="77 Kg", eyesColor="blue", birthYear="19BBY")
