package com.example.savch_andgit.auth.domain.usecase

import com.example.savch_andgit.auth.domain.repository.AuthRepository

class RegisterUserUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(login: String, password: String): Result<Unit> = repo.register(login, password)
}
