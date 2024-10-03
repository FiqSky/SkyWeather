package com.fiqsky.skyweather.api.response

data class WeatherBitResponse(
    val data: List<WeatherBitData>
)

data class WeatherBitData(
    val temp: Double,
    val rh: Int,
    val weather: WeatherBitWeather,
    val wind_spd: Double
)

data class WeatherBitWeather(
    val description: String
)
