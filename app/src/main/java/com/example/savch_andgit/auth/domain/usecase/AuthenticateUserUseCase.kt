package com.example.savch_andgit.auth.domain.usecase

import com.example.savch_andgit.auth.domain.repository.AuthRepository

class AuthenticateUserUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(login: String, password: String): Boolean = repo.authenticate(login, password)
}
