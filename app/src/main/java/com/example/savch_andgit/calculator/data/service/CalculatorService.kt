package com.example.savch_andgit.calculator.data.service

import com.example.savch_andgit.calculator.domain.model.CalculationResult
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorService {
    
    suspend fun calculate(expression: String): CalculationResult {
        return try {
            if (expression.isBlank()) {
                return CalculationResult(null, "Empty expression")
            }
            
            val result = ExpressionBuilder(expression)
                .build()
                .evaluate()
                
            CalculationResult(result)
        } catch (e: Exception) {
            CalculationResult(null, e.message ?: "Calculation error")
        }
    }
}
