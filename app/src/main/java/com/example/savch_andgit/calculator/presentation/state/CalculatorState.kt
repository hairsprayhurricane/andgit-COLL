package com.example.savch_andgit.calculator.presentation.state

data class CalculatorState(
    val expression: String = "",
    val result: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
