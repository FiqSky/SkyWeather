package com.fiqsky.skyweather

/**
author Fiqih
Copyright 2023, FiqSky Project
 **/
object WeatherRepository {

    suspend fun fetchWeatherFromAPIs(): List<WeatherData> {
        val weatherList = mutableListOf<WeatherData>()

        // Contoh API OpenWeatherMap, tambahkan API lain sesuai kebutuhan
        val apiKeyOpenWeather = "9b5f6e2345f474c8cf3628907faf48fd"

        try {
            val response = RetrofitClient.instance.getWeather(apiKey = apiKeyOpenWeather)
            if (response.isSuccessful) {
                val weather = response.body()?.let {
                    WeatherData(
                        temperature = it.main.temp,
                        description = it.weather[0].description,
                        humidity = it.main.humidity,
                        windSpeed = it.wind.speed
                    )
                }
                weather?.let { weatherList.add(it) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Tambahkan logika untuk menghubungi API cuaca lainnya

        return weatherList
    }
}
