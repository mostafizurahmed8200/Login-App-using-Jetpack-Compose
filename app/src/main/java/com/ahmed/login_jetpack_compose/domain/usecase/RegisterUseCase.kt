package com.ahmed.login_jetpack_compose.domain.usecase

import com.ahmed.login_jetpack_compose.domain.model.LoginModel
import com.ahmed.login_jetpack_compose.domain.repository.LoginRepository
import com.ahmed.login_jetpack_compose.util.ValidationUtils
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String):
            Result<LoginModel> {

        if (!ValidationUtils.isValidEmail(email)) {
            return Result.failure(Exception("Invalid email address"))
        }
        if (password.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }
        if (loginRepository.isEmailExists(email.trim())) {
            return Result.failure(Exception("Email already registered"))
        }
        return loginRepository.register(email, password)
    }

}