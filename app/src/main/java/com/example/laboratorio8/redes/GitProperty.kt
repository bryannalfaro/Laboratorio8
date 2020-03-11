package com.example.laboratorio8.redes

import com.squareup.moshi.Json

data class GitProperty(
    val login:String,
    @Json(name = "avatar_url")
    val imgSrcUrl: String

)