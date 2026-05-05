package com.ahmed.login_jetpack_compose.domain.repository

import com.ahmed.login_jetpack_compose.domain.model.LoginModel

interface LoginRepository {
    suspend fun register(email: String, password: String): Result<LoginModel>
    suspend fun login(email: String, password: String): Result<LoginModel>
    suspend fun forgetPassword(email: String, newPassword: String): Result<Boolean>
    suspend fun isEmailExists(email: String): Boolean
}