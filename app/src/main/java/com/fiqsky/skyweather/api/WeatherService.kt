package com.fiqsky.skyweather.api

import com.fiqsky.skyweather.api.response.OpenWeatherResponse
import com.fiqsky.skyweather.api.response.WeatherAPIResponse
import com.fiqsky.skyweather.api.response.WeatherBitResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
author Fiqih
Copyright 2023, FiqSky Project
 **/
interface WeatherService {

    // Endpoint untuk OpenWeatherMap
    @GET("weather")
    suspend fun getOpenWeather(
        @Query("q") city: String = "Surabaya",
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<OpenWeatherResponse>

    // Endpoint untuk WeatherAPI
    @GET("current.json")
    suspend fun getWeatherAPI(
        @Query("key") apiKey: String,
        @Query("q") city: String = "Surabaya"
    ): Response<WeatherAPIResponse>

    // Endpoint untuk WeatherBit
    @GET("current")
    suspend fun getWeatherBit(
        @Query("city") city: String = "Surabaya",
        @Query("key") apiKey: String
    ): Response<WeatherBitResponse>
}

