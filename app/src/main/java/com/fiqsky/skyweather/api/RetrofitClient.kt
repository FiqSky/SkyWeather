package com.fiqsky.skyweather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
author Fiqih
Copyright 2023, FiqSky Project
 **/
object RetrofitClients {

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // OpenWeatherMap Client
    val openWeatherClient: WeatherService by lazy {
        createRetrofit("https://api.openweathermap.org/data/2.5/").create(WeatherService::class.java)
    }

    // WeatherAPI Client
    val weatherAPIClient: WeatherService by lazy {
        createRetrofit("https://api.weatherapi.com/v1/").create(WeatherService::class.java)
    }

    // WeatherBit Client
    val weatherBitClient: WeatherService by lazy {
        createRetrofit("https://api.weatherbit.io/v2.0/").create(WeatherService::class.java)
    }
}