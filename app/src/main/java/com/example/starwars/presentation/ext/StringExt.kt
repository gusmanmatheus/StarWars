package com.example.starwars.presentation.ext

val radicalUrl = "https://starwars-visualguide.com/assets/img/"
val extensionUrl = ".jpg"
fun String.prepareUrl(type: String):String {
    val list = this.split("/")
    return radicalUrl + type +"/"+ list[5]  + extensionUrl
}