package com.example.savch_andgit.weather.domain.model

data class Weather(
    val cityName: String,
    val currentTemp: Double,
    val condition: String,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Double,
    val windSpeed: Double,
    val visibility: Double,
    val uvIndex: Int,
    val precipitation: Double
)

data class WeatherForecast(
    val currentWeather: Weather,
    val forecast: List<ForecastDay>
)

data class ForecastDay(
    val day: String,
    val date: String,
    val condition: String,
    val maxTemp: Double,
    val minTemp: Double,
    val iconRes: Int
)
