package com.fiqsky.skyweather.api.response

data class WeatherAPIResponse(
    val current: Current
)

data class Current(
    val temp_c: Double,
    val humidity: Int,
    val condition: Condition,
    val wind_kph: Double
)

data class Condition(
    val text: String
)
