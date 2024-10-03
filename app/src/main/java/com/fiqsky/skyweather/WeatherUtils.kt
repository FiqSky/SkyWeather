package com.fiqsky.skyweather

/**
author Fiqih
Copyright 2023, FiqSky Project
 **/
object WeatherUtils {

    fun calculateMostLikelyWeather(data: List<WeatherData>): WeatherData {
        val avgTemperature = data.map { it.temperature }.average()
        val avgHumidity = data.map { it.humidity }.average()
        val avgWindSpeed = data.map { it.windSpeed }.average()

        // Majority voting untuk deskripsi cuaca
        val majorityDescription = data.groupBy { it.description }
            .maxByOrNull { it.value.size }?.key ?: "Clear"

        return WeatherData(
            temperature = avgTemperature,
            description = majorityDescription,
            humidity = avgHumidity.toInt(),
            windSpeed = avgWindSpeed
        )
    }
}
