package com.example.savch_andgit.calculator.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.savch_andgit.R
import com.example.savch_andgit.calculator.presentation.viewmodel.CalculatorViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculatorFragment : Fragment() {
    private val viewModel: CalculatorViewModel by viewModel()
    private lateinit var tvExpression: TextView
    private lateinit var tvResult: TextView
    private lateinit var tvError: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvExpression = view.findViewById(R.id.tvExpression)
        tvResult = view.findViewById(R.id.tvResult)
        tvError = view.findViewById(R.id.tvError)
        setupClickListeners(view)
        observeViewModel()
    }

    private fun setupClickListeners(root: View) {
        root.findViewById<Button>(R.id.btnClear).setOnClickListener { viewModel.clear() }
        root.findViewById<Button>(R.id.btnDelete).setOnClickListener { viewModel.deleteLast() }
        root.findViewById<Button>(R.id.btnEquals).setOnClickListener { viewModel.calculate() }
        root.findViewById<Button>(R.id.btn0).setOnClickListener { appendToExpression("0") }
        root.findViewById<Button>(R.id.btn1).setOnClickListener { appendToExpression("1") }
        root.findViewById<Button>(R.id.btn2).setOnClickListener { appendToExpression("2") }
        root.findViewById<Button>(R.id.btn3).setOnClickListener { appendToExpression("3") }
        root.findViewById<Button>(R.id.btn4).setOnClickListener { appendToExpression("4") }
        root.findViewById<Button>(R.id.btn5).setOnClickListener { appendToExpression("5") }
        root.findViewById<Button>(R.id.btn6).setOnClickListener { appendToExpression("6") }
        root.findViewById<Button>(R.id.btn7).setOnClickListener { appendToExpression("7") }
        root.findViewById<Button>(R.id.btn8).setOnClickListener { appendToExpression("8") }
        root.findViewById<Button>(R.id.btn9).setOnClickListener { appendToExpression("9") }
        root.findViewById<Button>(R.id.btnPlus).setOnClickListener { appendToExpression("+") }
        root.findViewById<Button>(R.id.btnMinus).setOnClickListener { appendToExpression("-") }
        root.findViewById<Button>(R.id.btnMultiply).setOnClickListener { appendToExpression("*") }
        root.findViewById<Button>(R.id.btnDivide).setOnClickListener { appendToExpression("/") }
        root.findViewById<Button>(R.id.btnDot).setOnClickListener { appendToExpression(".") }
        root.findViewById<Button>(R.id.btnParentheses).setOnClickListener { appendToExpression("()") }
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
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            tvExpression.text = state.expression
            tvResult.text = if (state.result.isNotEmpty()) "= ${state.result}" else ""
            tvError.text = state.error
            tvResult.visibility = if (state.result.isNotEmpty()) View.VISIBLE else View.GONE
            tvError.visibility = if (state.error != null) View.VISIBLE else View.GONE
        })
    }
}
