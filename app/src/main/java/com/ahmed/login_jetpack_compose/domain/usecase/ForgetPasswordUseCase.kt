package com.ahmed.login_jetpack_compose.domain.usecase

import com.ahmed.login_jetpack_compose.domain.repository.LoginRepository
import com.ahmed.login_jetpack_compose.util.ValidationUtils
import javax.inject.Inject

class ForgetPasswordUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(
        email: String,
        newPassword: String,
        confirmPassword: String
    ): Result<Boolean> {

        if (!ValidationUtils.isValidEmail(email.trim())) {
            return Result.failure(Exception("Invalid email address"))
        }
        if (newPassword.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }
        if (newPassword != confirmPassword) {
            return Result.failure(Exception("Password don't match"))
        }

        return loginRepository.forgetPassword(email.trim(), newPassword)
    }
}