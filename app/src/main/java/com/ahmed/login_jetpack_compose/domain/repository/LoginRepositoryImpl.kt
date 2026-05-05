package com.ahmed.login_jetpack_compose.domain.repository

import com.ahmed.login_jetpack_compose.data.local.dao.LoginDao
import com.ahmed.login_jetpack_compose.data.local.entity.LoginEntity
import com.ahmed.login_jetpack_compose.data.mapper.toModel
import com.ahmed.login_jetpack_compose.domain.model.LoginModel
import java.util.UUID
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDao: LoginDao
) : LoginRepository {
    override suspend fun register(
        email: String,
        password: String
    ): Result<LoginModel> {
        return try {
            val userId = UUID.randomUUID().toString()  // Unique random ID
            val entity = LoginEntity(userId = userId, email = email, password = password)
            loginDao.insertLogin(entity)
            Result.success(entity.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginModel> {
        return try {
            val entity = loginDao.getLoginByEmailAndPassword(email = email, password = password)

            if (entity != null) {
                Result.success(entity.toModel())
            } else {
                Result.failure(Exception("Invalid email or password"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun forgetPassword(
        email: String,
        newPassword: String
    ): Result<Boolean> {
        return try {
            val rowsUpdated = loginDao.updatePassword(email, newPassword)
            if (rowsUpdated > 0) {
                Result.success(true)
            } else {
                Result.failure(Exception("Email is not found. Please register first."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isEmailExists(email: String): Boolean {
        return loginDao.isEmailExists(email) > 0
    }
}