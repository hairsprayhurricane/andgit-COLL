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
import com.example.savch_andgit.auth.data.local.AppDatabase
import com.example.savch_andgit.auth.data.repository.AuthRepositoryImpl
import com.example.savch_andgit.auth.domain.repository.AuthRepository
import com.example.savch_andgit.auth.domain.usecase.AuthenticateUserUseCase
import com.example.savch_andgit.auth.domain.usecase.RegisterUserUseCase
import com.example.savch_andgit.auth.presentation.viewmodel.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import androidx.room.Room

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

val authModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app.db").build() }
    single { get<AppDatabase>().userDao() }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { RegisterUserUseCase(get()) }
    single { AuthenticateUserUseCase(get()) }
    viewModel { AuthViewModel(get(), get()) }
}

val appModule = listOf(calculatorModule, weatherModule, authModule)
