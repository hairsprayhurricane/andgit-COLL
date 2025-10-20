package com.example.savch_andgit.calculator.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.savch_andgit.R
import com.example.savch_andgit.calculator.presentation.viewmodel.CalculatorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorActivity : AppCompatActivity() {
    
    private val viewModel: CalculatorViewModel by viewModel()
    
    private lateinit var tvExpression: TextView
    private lateinit var tvResult: TextView
    private lateinit var tvError: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        
        initViews()
        setupClickListeners()
        observeViewModel()
    }
    
    private fun initViews() {
        tvExpression = findViewById(R.id.tvExpression)
        tvResult = findViewById(R.id.tvResult)
        tvError = findViewById(R.id.tvError)
        
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { onBackPressed() }
        }
    }
    
    private fun setupClickListeners() {
        findViewById<Button>(R.id.btnClear).setOnClickListener { viewModel.clear() }
        findViewById<Button>(R.id.btnDelete).setOnClickListener { viewModel.deleteLast() }
        findViewById<Button>(R.id.btnEquals).setOnClickListener { viewModel.calculate() }
        
        findViewById<Button>(R.id.btn0).setOnClickListener { appendToExpression("0") }
        findViewById<Button>(R.id.btn1).setOnClickListener { appendToExpression("1") }
        findViewById<Button>(R.id.btn2).setOnClickListener { appendToExpression("2") }
        findViewById<Button>(R.id.btn3).setOnClickListener { appendToExpression("3") }
        findViewById<Button>(R.id.btn4).setOnClickListener { appendToExpression("4") }
        findViewById<Button>(R.id.btn5).setOnClickListener { appendToExpression("5") }
        findViewById<Button>(R.id.btn6).setOnClickListener { appendToExpression("6") }
        findViewById<Button>(R.id.btn7).setOnClickListener { appendToExpression("7") }
        findViewById<Button>(R.id.btn8).setOnClickListener { appendToExpression("8") }
        findViewById<Button>(R.id.btn9).setOnClickListener { appendToExpression("9") }
        
        findViewById<Button>(R.id.btnPlus).setOnClickListener { appendToExpression("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { appendToExpression("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { appendToExpression("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { appendToExpression("/") }
        findViewById<Button>(R.id.btnDot).setOnClickListener { appendToExpression(".") }
        findViewById<Button>(R.id.btnParentheses).setOnClickListener { appendToExpression("()") }
    }
    
    private fun appendToExpression(value: String) {
        val currentExpression = tvExpression.text.toString()
        val newExpression = if (value == "()") {
            if (currentExpression.isEmpty() || currentExpression.last() !in "0123456789)") {
                currentExpression + "("
            } else {
                currentExpression + ")"
            }
        } else {
            currentExpression + value
        }
        viewModel.updateExpression(newExpression)
    }
    
    private fun observeViewModel() {
        viewModel.state.observe(this, Observer { state ->
            tvExpression.text = state.expression
            tvResult.text = if (state.result.isNotEmpty()) "= ${state.result}" else ""
            tvError.text = state.error
            
            tvResult.visibility = if (state.result.isNotEmpty()) View.VISIBLE else View.GONE
            tvError.visibility = if (state.error != null) View.VISIBLE else View.GONE
        })
    }
}
