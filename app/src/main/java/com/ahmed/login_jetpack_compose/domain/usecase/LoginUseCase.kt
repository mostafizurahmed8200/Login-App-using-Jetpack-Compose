package com.ahmed.login_jetpack_compose.domain.usecase

import com.ahmed.login_jetpack_compose.domain.model.LoginModel
import com.ahmed.login_jetpack_compose.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<LoginModel> {

        if (email.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Email and password can't be empty"))
        }
        return loginRepository.login(email, password)

    }
}