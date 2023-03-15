package com.example.starwars.data

import com.google.gson.annotations.SerializedName

data class PeopleRemote(
    @SerializedName("url")
    val image:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("height")
    val height:String,
    @SerializedName("mass")
    val mass:String
)