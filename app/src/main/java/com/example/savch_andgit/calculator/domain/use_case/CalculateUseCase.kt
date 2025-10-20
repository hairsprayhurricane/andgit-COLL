package com.example.savch_andgit.calculator.domain.use_case

import com.example.savch_andgit.calculator.domain.model.CalculationResult
import com.example.savch_andgit.calculator.domain.repository.CalculatorRepository

class CalculateUseCase(
    private val repository: CalculatorRepository
) {
    suspend operator fun invoke(expression: String): CalculationResult {
        return repository.calculate(expression)
    }
}
