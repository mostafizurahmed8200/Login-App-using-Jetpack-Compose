package com.ahmed.login_jetpack_compose.ui.presentation.forgetpassword

data class ForgetPasswordUiState(
    val email: String = "",
    val newPassword: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)