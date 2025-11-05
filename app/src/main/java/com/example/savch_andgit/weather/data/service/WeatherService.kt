package com.example.savch_andgit.weather.data.service

import com.example.savch_andgit.R
import com.example.savch_andgit.weather.domain.model.*
import kotlinx.coroutines.delay

class WeatherService {
    
    suspend fun getWeatherForecast(): WeatherForecast {
        delay(2000)
        
        val currentWeather = Weather(
            cityName = "Moscow",
            currentTemp = 22.0,
            condition = "Облачно",
            feelsLike = 25.0,
            humidity = 65,
            pressure = 1013.0,
            windSpeed = 12.0,
            visibility = 10.0,
            uvIndex = 3,
            precipitation = 0.0
        )
        
        val base = listOf(
            ForecastDay("Сегодня", "20 окт", "Облачно", 22.0, 15.0, R.drawable.ic_cloudy),
            ForecastDay("Завтра", "21 окт", "Солнечно", 25.0, 18.0, R.drawable.ic_sunny),
            ForecastDay("Среда", "22 окт", "Дождь", 18.0, 12.0, R.drawable.ic_rainy),
            ForecastDay("Четверг", "23 окт", "Облачно", 20.0, 14.0, R.drawable.ic_cloudy),
            ForecastDay("Пятница", "24 окт", "Солнечно", 24.0, 16.0, R.drawable.ic_sunny),
            ForecastDay("Суббота", "25 окт", "Облачно", 21.0, 13.0, R.drawable.ic_cloudy),
            ForecastDay("Воскресенье", "26 окт", "Дождь", 19.0, 11.0, R.drawable.ic_rainy)
        )
        val forecast = (base + base.map { it.copy() } + base.map { it.copy() })
        
        return WeatherForecast(currentWeather, forecast)
    }
}
