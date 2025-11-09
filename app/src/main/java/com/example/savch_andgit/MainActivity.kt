package com.example.savch_andgit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.app.Activity
import com.example.savch_andgit.calculator.presentation.ui.CalculatorActivity
import com.example.savch_andgit.weather.presentation.ui.WeatherActivity

// TODO: Временная активити для навигации/отладки. Удалить в следующих коммитах после решения проблемы с JPEG кокоса.
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        findViewById<Button>(R.id.btnCalculator).setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }
        
        findViewById<Button>(R.id.btnWeather).setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))
        }
    }
}