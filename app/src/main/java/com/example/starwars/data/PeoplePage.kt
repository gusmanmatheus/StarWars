package com.example.starwars.data

import com.google.gson.annotations.SerializedName

data class PeoplePage(
    @SerializedName("next")
    var nextPage:String,
    @SerializedName("previous")
    var previousPage:String,
    @SerializedName("results")
    val peopleList: List<PeopleRemote>
)