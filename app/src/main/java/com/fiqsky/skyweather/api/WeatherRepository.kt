package com.fiqsky.skyweather.api

import android.util.Log
import com.fiqsky.skyweather.utils.WeatherData

/**
author Fiqih
Copyright 2023, FiqSky Project
 **/
object WeatherRepository {

    suspend fun fetchWeatherFromAPIs(): List<WeatherData> {
        val weatherList = mutableListOf<WeatherData>()
        val tag = "WeatherRepository" // Tag untuk log

        // API keys
        val apiKeyOpenWeather = "9b5f6e2345f474c8cf3628907faf48fd"
        val apiKeyWeatherAPI = "2b115c5671ff4aecb8894047240310"
        val apiKeyWeatherBit = "3b7a23a87afe4d5c97c7be2f4a916ec7"

        // Fetch data from OpenWeatherMap
        try {
            val response = RetrofitClients.openWeatherClient.getOpenWeather(apiKey = apiKeyOpenWeather)
            if (response.isSuccessful) {
                val weather = response.body()?.let {
                    WeatherData(
                        temperature = it.main.temp,
                        description = it.weather[0].description,
                        humidity = it.main.humidity,
                        windSpeed = it.wind.speed
                    )
                }
                weather?.let {
                    weatherList.add(it)
                    Log.d(tag, "OpenWeatherMap: Temp = ${it.temperature}, Desc = ${it.description}, Humidity = ${it.humidity}, WindSpeed = ${it.windSpeed}")
                }
            } else {
                Log.d(tag, "OpenWeatherMap: Failed response, code = ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e(tag, "OpenWeatherMap: Error fetching data", e)
        }

        // Fetch data from WeatherAPI
        try {
            val response = RetrofitClients.weatherAPIClient.getWeatherAPI(apiKey = apiKeyWeatherAPI)
            if (response.isSuccessful) {
                val weather = response.body()?.let {
                    WeatherData(
                        temperature = it.current.temp_c,
                        description = it.current.condition.text,
                        humidity = it.current.humidity,
                        windSpeed = it.current.wind_kph
                    )
                }
                weather?.let {
                    weatherList.add(it)
                    Log.d(tag, "WeatherAPI: Temp = ${it.temperature}, Desc = ${it.description}, Humidity = ${it.humidity}, WindSpeed = ${it.windSpeed}")
                }
            } else {
                Log.d(tag, "WeatherAPI: Failed response, code = ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e(tag, "WeatherAPI: Error fetching data", e)
        }

        // Fetch data from WeatherBit
        try {
            val response = RetrofitClients.weatherBitClient.getWeatherBit(apiKey = apiKeyWeatherBit)
            if (response.isSuccessful) {
                val weather = response.body()?.let {
                    WeatherData(
                        temperature = it.data[0].temp,
                        description = it.data[0].weather.description,
                        humidity = it.data[0].rh,
                        windSpeed = it.data[0].wind_spd
                    )
                }
                weather?.let {
                    weatherList.add(it)
                    Log.d(tag, "WeatherBit: Temp = ${it.temperature}, Desc = ${it.description}, Humidity = ${it.humidity}, WindSpeed = ${it.windSpeed}")
                }
            } else {
                Log.d(tag, "WeatherBit: Failed response, code = ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e(tag, "WeatherBit: Error fetching data", e)
        }

        return weatherList
    }
}

