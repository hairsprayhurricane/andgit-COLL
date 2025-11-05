package com.example.savch_andgit.auth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savch_andgit.auth.domain.usecase.AuthenticateUserUseCase
import com.example.savch_andgit.auth.domain.usecase.RegisterUserUseCase
import kotlinx.coroutines.launch

data class AuthState(
    val isLoading: Boolean = false,
    val isAuthorized: Boolean = false,
    val error: String? = null
)

class AuthViewModel(
    private val authenticate: AuthenticateUserUseCase,
    private val registerUser: RegisterUserUseCase
) : ViewModel() {
    private val _state = MutableLiveData(AuthState())
    val state: LiveData<AuthState> = _state
    private val _loginSuccess = MutableLiveData(false)
    val loginSuccess: LiveData<Boolean> = _loginSuccess
    private val _registerSuccess = MutableLiveData(false)
    val registerSuccess: LiveData<Boolean> = _registerSuccess

    fun login(login: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true, error = null)
            val ok = authenticate(login, password)
            _state.value = _state.value?.copy(isLoading = false, isAuthorized = ok, error = if (ok) null else "auth_failed")
            if (ok) _loginSuccess.value = true
        }
    }

    fun register(login: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true, error = null)
            val res = registerUser(login, password)
            if (res.isSuccess) {
                _state.value = _state.value?.copy(isLoading = false, error = null)
                _registerSuccess.value = true
            } else {
                _state.value = _state.value?.copy(isLoading = false, error = res.exceptionOrNull()?.message ?: "register_failed")
            }
        }
    }

    fun consumeLoginSuccess() { _loginSuccess.value = false }
    fun consumeRegisterSuccess() { _registerSuccess.value = false }
}
