package com.example.savch_andgit.weather.data.repository

import com.example.savch_andgit.weather.data.service.WeatherService
import com.example.savch_andgit.weather.domain.model.WeatherForecast
import com.example.savch_andgit.weather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {
    
    override suspend fun getWeatherForecast(): WeatherForecast {
        return weatherService.getWeatherForecast()
    }
}
