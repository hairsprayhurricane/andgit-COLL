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
import com.example.savch_andgit.music.data.service.ItunesService
import com.example.savch_andgit.music.data.repository.MusicRepositoryImpl
import com.example.savch_andgit.music.domain.repository.MusicRepository
import com.example.savch_andgit.music.domain.usecase.GetFavoriteTracksUseCase
import com.example.savch_andgit.music.domain.usecase.RemoveFavoriteTrackUseCase
import com.example.savch_andgit.music.domain.usecase.SaveFavoriteTrackUseCase
import com.example.savch_andgit.music.domain.usecase.SearchTracksUseCase
import com.example.savch_andgit.music.presentation.viewmodel.MusicViewModel
import com.example.savch_andgit.music.presentation.viewmodel.FavoritesViewModel

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
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app.db").addMigrations(AppDatabase.MIGRATION_1_2).build() }
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().trackDao() }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { RegisterUserUseCase(get()) }
    single { AuthenticateUserUseCase(get()) }
    viewModel { AuthViewModel(get(), get()) }
}

val musicModule = module {
    single { ItunesService() }
    single<MusicRepository> { MusicRepositoryImpl(get(), get()) }
    single { SearchTracksUseCase(get()) }
    single { SaveFavoriteTrackUseCase(get()) }
    single { GetFavoriteTracksUseCase(get()) }
    single { RemoveFavoriteTrackUseCase(get()) }
    viewModel { MusicViewModel(get(), get(), get(), get()) }
    viewModel { FavoritesViewModel(get(), get()) }
}

val appModule = listOf(calculatorModule, weatherModule, authModule, musicModule)
