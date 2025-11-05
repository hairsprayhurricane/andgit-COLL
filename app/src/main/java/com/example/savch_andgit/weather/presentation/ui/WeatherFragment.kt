package com.example.savch_andgit.weather.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savch_andgit.R
import com.example.savch_andgit.weather.presentation.adapter.WeatherForecastAdapter
import com.example.savch_andgit.weather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cityNameTextView = view.findViewById(R.id.cityNameTextView)
        currentTempTextView = view.findViewById(R.id.currentTempTextView)
        currentConditionTextView = view.findViewById(R.id.currentConditionTextView)
        currentFeelsLikeTextView = view.findViewById(R.id.currentFeelsLikeTextView)
        currentWeatherIconImageView = view.findViewById(R.id.currentWeatherIconImageView)
        loadingLayout = view.findViewById(R.id.loadingLayout)
        errorLayout = view.findViewById(R.id.errorLayout)
        forecastContainer = view.findViewById(R.id.forecastContainer)
        weatherRecyclerView = view.findViewById(R.id.weatherRecyclerView)
        retryButton = view.findViewById(R.id.retryButton)
        weatherRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        forecastAdapter = WeatherForecastAdapter(emptyList())
        weatherRecyclerView.adapter = forecastAdapter
        retryButton.setOnClickListener { viewModel.loadWeather() }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
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
