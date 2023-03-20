package com.example.starwars.presentation.model

import com.example.starwars.data.model.PeopleRemote
import java.io.Serializable

data class People(
    val image: String,
    val name: String,
    val height: String,
    val mass: String
): Serializable

fun PeopleRemote.toPeople() = People(
    image = image,
    name = name,
    height = height+"Kg",
    mass = "${mass.toDouble()/100} Metros",
)
