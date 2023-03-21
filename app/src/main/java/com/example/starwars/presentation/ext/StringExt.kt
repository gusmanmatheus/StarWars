package com.example.starwars.presentation.ext


fun String.prepareUrl(type: String):String {
    val radicalUrl = "https://starwars-visualguide.com/assets/img/"
    val extensionUrl = ".jpg"
    val list = this.split("/")
    return radicalUrl + type +"/"+ list[5]  + extensionUrl
}

fun String.dateFormatter():String {
   val date = this.split("-")
   return "${date[2]}/${date[1]}/${date[0]}"
}