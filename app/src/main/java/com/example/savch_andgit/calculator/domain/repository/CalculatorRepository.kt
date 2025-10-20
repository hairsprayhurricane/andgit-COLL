package com.example.savch_andgit.calculator.domain.repository

import com.example.savch_andgit.calculator.domain.model.CalculationResult

interface CalculatorRepository {
    suspend fun calculate(expression: String): CalculationResult
}
