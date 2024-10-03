package com.fiqsky.skyweather.api.response

/**
author Fiqih
Copyright 2023, FiqSky Project
 **/
data class OpenWeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val wind: OpenWeatherWind
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Weather(
    val description: String
)

data class OpenWeatherWind(
    val speed: Double
)
