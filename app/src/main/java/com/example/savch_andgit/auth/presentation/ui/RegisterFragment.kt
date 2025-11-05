package com.example.savch_andgit.auth.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.savch_andgit.R
import com.example.savch_andgit.auth.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {
    private val viewModel: AuthViewModel by viewModel()
    private lateinit var loginField: EditText
    private lateinit var passwordField: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginField = view.findViewById(R.id.etLogin)
        passwordField = view.findViewById(R.id.etPassword)
        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            val login = loginField.text?.toString()?.trim().orEmpty()
            val password = passwordField.text?.toString()?.trim().orEmpty()
            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.fields_empty), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.register(login, password)
            }
        }
        viewModel.registerSuccess.observe(viewLifecycleOwner) { success ->
            if (success == true) {
                viewModel.consumeRegisterSuccess()
                findNavController().navigate(R.id.action_register_to_auth)
            }
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            state.error?.let {
                val msg = when (it) {
                    "user_exists" -> getString(R.string.user_exists)
                    else -> getString(R.string.register_failed)
                }
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
