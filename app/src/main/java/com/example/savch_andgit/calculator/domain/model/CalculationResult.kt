package com.example.savch_andgit.calculator.domain.model

data class CalculationResult(
    val result: Double?,
    val error: String? = null,
    val isSuccess: Boolean = error == null
)
