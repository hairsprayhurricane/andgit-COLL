package com.example.savch_andgit.weather.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savch_andgit.R
import com.example.savch_andgit.weather.presentation.adapter.WeatherForecastAdapter
import com.example.savch_andgit.weather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : AppCompatActivity() {
    
    private val viewModel: WeatherViewModel by viewModel()
    
    private lateinit var cityNameTextView: TextView
    private lateinit var currentTempTextView: TextView
    private lateinit var currentConditionTextView: TextView
    private lateinit var currentFeelsLikeTextView: TextView
    private lateinit var currentWeatherIconImageView: ImageView
    private lateinit var loadingLayout: View
    private lateinit var errorLayout: View
    private lateinit var forecastContainer: View
    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var retryButton: Button
    private lateinit var forecastAdapter: WeatherForecastAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        
        initViews()
        setupClickListeners()
        observeViewModel()
    }
    
    private fun initViews() {
        cityNameTextView = findViewById(R.id.cityNameTextView)
        currentTempTextView = findViewById(R.id.currentTempTextView)
        currentConditionTextView = findViewById(R.id.currentConditionTextView)
        currentFeelsLikeTextView = findViewById(R.id.currentFeelsLikeTextView)
        currentWeatherIconImageView = findViewById(R.id.currentWeatherIconImageView)
        loadingLayout = findViewById(R.id.loadingLayout)
        errorLayout = findViewById(R.id.errorLayout)
        forecastContainer = findViewById(R.id.forecastContainer)
        weatherRecyclerView = findViewById(R.id.weatherRecyclerView)
        retryButton = findViewById(R.id.retryButton)
        
        setupRecyclerView()
        
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { onBackPressed() }
        }
    }
    
    private fun setupRecyclerView() {
        weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        forecastAdapter = WeatherForecastAdapter(emptyList())
        weatherRecyclerView.adapter = forecastAdapter
    }
    
    private fun setupClickListeners() {
        retryButton.setOnClickListener {
            viewModel.loadWeather()
        }
    }
    
    private fun observeViewModel() {
        viewModel.state.observe(this, Observer { state ->
            when {
                state.isLoading -> {
                    loadingLayout.visibility = View.VISIBLE
                    errorLayout.visibility = View.GONE
                    forecastContainer.visibility = View.GONE
                }
                state.error != null -> {
                    loadingLayout.visibility = View.GONE
                    errorLayout.visibility = View.VISIBLE
                    forecastContainer.visibility = View.GONE
                }
                state.weatherForecast != null -> {
                    loadingLayout.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    forecastContainer.visibility = View.VISIBLE
                    updateWeatherDisplay(state.weatherForecast)
                }
            }
        })
    }
    
    private fun updateWeatherDisplay(weatherForecast: com.example.savch_andgit.weather.domain.model.WeatherForecast) {
        val weather = weatherForecast.currentWeather
        cityNameTextView.text = weather.cityName
        currentTempTextView.text = "${weather.currentTemp.toInt()}°C"
        currentConditionTextView.text = weather.condition
        currentFeelsLikeTextView.text = "Ощущается как ${weather.feelsLike.toInt()}°C"
        currentWeatherIconImageView.setImageResource(getWeatherIcon(weather.condition))
        
        forecastAdapter = WeatherForecastAdapter(weatherForecast.forecast)
        weatherRecyclerView.adapter = forecastAdapter
    }
    
    private fun getWeatherIcon(condition: String): Int {
        return when (condition) {
            "Солнечно" -> R.drawable.ic_sunny
            "Облачно" -> R.drawable.ic_cloudy
            "Дождь" -> R.drawable.ic_rainy
            else -> R.drawable.ic_cloudy
        }
    }
}
