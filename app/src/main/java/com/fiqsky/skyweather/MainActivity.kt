package com.fiqsky.skyweather

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fiqsky.skyweather.api.WeatherRepository
import com.fiqsky.skyweather.utils.WeatherUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    // Deklarasi TextView
    private lateinit var textViewTemperature: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var textViewHumidity: TextView
    private lateinit var textViewWindSpeed: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Jika tidak diperlukan, hapus fungsi enableEdgeToEdge()
        // enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        // Inisialisasi TextView dari layout
        textViewTemperature = findViewById(R.id.textViewTemperature)
        textViewDescription = findViewById(R.id.textViewDescription)
        textViewHumidity = findViewById(R.id.textViewHumidity)
        textViewWindSpeed = findViewById(R.id.textViewWindSpeed)

        // Menampilkan data cuaca saat aplikasi dimulai
        fetchAndDisplayWeather()
    }

    @SuppressLint("SetTextI18n")
    private fun fetchAndDisplayWeather() {
        // Gunakan coroutine untuk operasi jaringan di background thread
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Panggil fungsi fetchWeatherFromAPIs yang ada di WeatherRepository
                val weatherDataList = WeatherRepository.fetchWeatherFromAPIs()

                // Panggil fungsi calculateMostLikelyWeather yang ada di WeatherUtils
                val mostLikelyWeather = WeatherUtils.calculateMostLikelyWeather(weatherDataList)

                // Tampilkan di UI (Main Thread)
                withContext(Dispatchers.Main) {
                    textViewTemperature.text = "Suhu: ${mostLikelyWeather.temperature}°C"
                    textViewDescription.text = "Deskripsi: ${mostLikelyWeather.description}"
                    textViewHumidity.text = "Kelembaban: ${mostLikelyWeather.humidity}%"
                    textViewWindSpeed.text = "Kecepatan Angin: ${mostLikelyWeather.windSpeed} m/s"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}