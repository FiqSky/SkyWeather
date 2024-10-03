package com.fiqsky.skyweather

/**
author Fiqih
Copyright 2023, FiqSky Project
 **/
data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Weather(
    val description: String
)

data class Wind(
    val speed: Double
)
