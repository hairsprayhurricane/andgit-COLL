package com.example.savch_andgit.auth.domain.repository

interface AuthRepository {
    suspend fun register(login: String, password: String): Result<Unit>
    suspend fun authenticate(login: String, password: String): Boolean
}
