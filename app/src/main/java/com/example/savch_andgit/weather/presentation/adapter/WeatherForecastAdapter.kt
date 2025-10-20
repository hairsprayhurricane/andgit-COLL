package com.example.savch_andgit.weather.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.savch_andgit.R
import com.example.savch_andgit.weather.domain.model.ForecastDay

class WeatherForecastAdapter(
    private val forecast: List<ForecastDay>
) : RecyclerView.Adapter<WeatherForecastAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val conditionTextView: TextView = itemView.findViewById(R.id.conditionTextView)
        val maxTempTextView: TextView = itemView.findViewById(R.id.maxTempTextView)
        val minTempTextView: TextView = itemView.findViewById(R.id.minTempTextView)
        val weatherIconImageView: ImageView = itemView.findViewById(R.id.weatherIconImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_forecast, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val forecastDay = forecast[position]
        
        holder.dayTextView.text = forecastDay.day
        holder.dateTextView.text = forecastDay.date
        holder.conditionTextView.text = forecastDay.condition
        holder.maxTempTextView.text = "${forecastDay.maxTemp.toInt()}°"
        holder.minTempTextView.text = "${forecastDay.minTemp.toInt()}°"
        
        holder.weatherIconImageView.setImageResource(forecastDay.iconRes)
    }

    override fun getItemCount(): Int = forecast.size
}
