package com.example.savch_andgit.weather.domain.repository

import com.example.savch_andgit.weather.domain.model.WeatherForecast

interface WeatherRepository {
    suspend fun getWeatherForecast(): WeatherForecast
}
