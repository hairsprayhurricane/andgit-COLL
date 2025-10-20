package com.example.savch_andgit.weather.domain.use_case

import com.example.savch_andgit.weather.domain.model.WeatherForecast
import com.example.savch_andgit.weather.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(): WeatherForecast {
        return repository.getWeatherForecast()
    }
}
