package com.example.savch_andgit.weather.presentation.state

import com.example.savch_andgit.weather.domain.model.WeatherForecast

data class WeatherState(
    val weatherForecast: WeatherForecast? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
