package com.example.savch_andgit.calculator.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savch_andgit.calculator.domain.use_case.CalculateUseCase
import com.example.savch_andgit.calculator.presentation.state.CalculatorState
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val calculateUseCase: CalculateUseCase
) : ViewModel() {
    
    private val _state = MutableLiveData(CalculatorState())
    val state: LiveData<CalculatorState> = _state
    
    fun updateExpression(expression: String) {
        _state.value = _state.value?.copy(expression = expression)
    }
    
    fun calculate() {
        val currentExpression = _state.value?.expression ?: return
        if (currentExpression.isBlank()) return
        
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true, error = null)
            
            val result = calculateUseCase(currentExpression)
            
            _state.value = _state.value?.copy(
                isLoading = false,
                result = if (result.isSuccess) {
                    result.result?.toString() ?: ""
                } else {
                    ""
                },
                error = result.error
            )
        }
    }
    
    fun clear() {
        _state.value = CalculatorState()
    }
    
    fun deleteLast() {
        val currentExpression = _state.value?.expression ?: return
        if (currentExpression.isNotEmpty()) {
            _state.value = _state.value?.copy(
                expression = currentExpression.dropLast(1),
                result = "",
                error = null
            )
        }
    }
}
