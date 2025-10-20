package com.example.savch_andgit.weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savch_andgit.weather.domain.use_case.GetWeatherUseCase
import com.example.savch_andgit.weather.presentation.state.WeatherState
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    
    private val _state = MutableLiveData(WeatherState(isLoading = true))
    val state: LiveData<WeatherState> = _state
    
    init {
        loadWeather()
    }
    
    fun loadWeather() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true, error = null)
            
            try {
                val weatherForecast = getWeatherUseCase()
                _state.value = _state.value?.copy(
                    weatherForecast = weatherForecast,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value?.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
}
