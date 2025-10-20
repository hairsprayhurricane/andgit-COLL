package com.example.savch_andgit.di

import com.example.savch_andgit.calculator.data.repository.CalculatorRepositoryImpl
import com.example.savch_andgit.calculator.data.service.CalculatorService
import com.example.savch_andgit.calculator.domain.repository.CalculatorRepository
import com.example.savch_andgit.calculator.domain.use_case.CalculateUseCase
import com.example.savch_andgit.calculator.presentation.viewmodel.CalculatorViewModel
import com.example.savch_andgit.weather.data.repository.WeatherRepositoryImpl
import com.example.savch_andgit.weather.data.service.WeatherService
import com.example.savch_andgit.weather.domain.repository.WeatherRepository
import com.example.savch_andgit.weather.domain.use_case.GetWeatherUseCase
import com.example.savch_andgit.weather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val calculatorModule = module {
    
    single<CalculatorService> { CalculatorService() }
    
    single<CalculatorRepository> { CalculatorRepositoryImpl(get()) }
    
    single<CalculateUseCase> { CalculateUseCase(get()) }
    
    viewModel { CalculatorViewModel(get()) }
}

val weatherModule = module {
    
    single<WeatherService> { WeatherService() }
    
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    
    single<GetWeatherUseCase> { GetWeatherUseCase(get()) }
    
    viewModel { WeatherViewModel(get()) }
}

val appModule = listOf(calculatorModule, weatherModule)
