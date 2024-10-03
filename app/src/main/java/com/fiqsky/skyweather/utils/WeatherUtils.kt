package com.fiqsky.skyweather.utils

/**
author Fiqih
Copyright 2023, FiqSky Project
 **/
object WeatherUtils {
    private val descriptionMapping = mapOf(
        "clear sky" to "Clear",
        "few clouds" to "Partly Cloudy",
        "scattered clouds" to "Partly Cloudy",
        "broken clouds" to "Cloudy",
        "overcast" to "Overcast",
        "light rain" to "Rain",
        "rain" to "Rain",
        "heavy rain" to "Rain",
        "snow" to "Snow",
        "sleet" to "Snow", // di-mapping ke Snow karena kesamaan
        "thunderstorm" to "Thunderstorm",
        "haze" to "Haze",
        "mist" to "Mist",
        "fog" to "Fog",
        "dust" to "Dust",
        "sand" to "Sand",
        "ash" to "Ash",
        "squall" to "Squall",
        "tornado" to "Tornado",
        "freezing rain" to "Rain", // di-mapping ke Rain
        "ice pellets" to "Rain", // di-mapping ke Rain
        "clear night" to "Clear",
        "few clouds night" to "Partly Cloudy",
        "scattered clouds night" to "Partly Cloudy",
        "broken clouds night" to "Cloudy",
        "overcast night" to "Overcast"
    )

    fun calculateMostLikelyWeather(data: List<WeatherData>): WeatherData {
        if (data.isEmpty()) return WeatherData(0.0, "Clear", 0, 0.0)

        var totalTemperature = 0.0
        var totalHumidity = 0
        var totalWindSpeed = 0.0
        val descriptionCount = mutableMapOf<String, Int>()

        data.forEach {
            totalTemperature += it.temperature
            totalHumidity += it.humidity
            totalWindSpeed += it.windSpeed

            // Normalisasi deskripsi cuaca
            val normalizedDescription = normalizeDescription(it.description.trim())
            descriptionCount[normalizedDescription] = descriptionCount.getOrDefault(normalizedDescription, 0) + 1
        }

        val avgTemperature = totalTemperature / data.size
        val avgHumidity = totalHumidity / data.size
        val avgWindSpeed = totalWindSpeed / data.size

        // Majority voting untuk deskripsi cuaca
        val majorityDescription = descriptionCount.maxByOrNull { it.value }?.key ?: "Clear"

        return WeatherData(
            temperature = avgTemperature,
            description = majorityDescription,
            humidity = avgHumidity.toInt(),
            windSpeed = avgWindSpeed
        )
    }

    private fun normalizeDescription(description: String): String {
        // Menggunakan lowercase untuk mencocokkan dengan kunci dalam mapping
        return descriptionMapping.entries.firstOrNull {
            it.key.equals(description.lowercase(), ignoreCase = true)
        }?.value ?: description // Kembalikan deskripsi asli jika tidak ada yang cocok
    }
}
