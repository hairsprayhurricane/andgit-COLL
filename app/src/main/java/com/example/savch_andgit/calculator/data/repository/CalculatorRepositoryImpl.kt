package com.example.savch_andgit.calculator.data.repository

import com.example.savch_andgit.calculator.data.service.CalculatorService
import com.example.savch_andgit.calculator.domain.model.CalculationResult
import com.example.savch_andgit.calculator.domain.repository.CalculatorRepository

class CalculatorRepositoryImpl(
    private val calculatorService: CalculatorService
) : CalculatorRepository {
    
    override suspend fun calculate(expression: String): CalculationResult {
        return calculatorService.calculate(expression)
    }
}
