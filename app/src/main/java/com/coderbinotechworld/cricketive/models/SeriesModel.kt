package com.coderbinotechworld.cricketive.models

data class SeriesModel(
    val apikey: String,
    val `data`: Data,
    val info: InfoX,
    val status: String
)