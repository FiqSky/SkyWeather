package com.fiqsky.skyweather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
author Fiqih
Copyright 2023, FiqSky Project
 **/
interface WeatherService {
    // Definisikan endpoint untuk OpenWeatherMap API
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String = "Surabaya", // Set default untuk Surabaya
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<WeatherResponse>
}