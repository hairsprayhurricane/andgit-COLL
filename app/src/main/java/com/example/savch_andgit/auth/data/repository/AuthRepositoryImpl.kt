package com.example.savch_andgit.auth.data.repository

import com.example.savch_andgit.auth.data.local.UserDao
import com.example.savch_andgit.auth.data.local.UserEntity
import com.example.savch_andgit.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val userDao: UserDao
) : AuthRepository {
    override suspend fun register(login: String, password: String): Result<Unit> {
        val existing = userDao.getByLogin(login)
        return if (existing != null) {
            Result.failure(IllegalStateException("user_exists"))
        } else {
            userDao.insert(UserEntity(login = login, password = password))
            Result.success(Unit)
        }
    }

    override suspend fun authenticate(login: String, password: String): Boolean {
        val user = userDao.getByLogin(login)
        return user?.password == password
    }
}
