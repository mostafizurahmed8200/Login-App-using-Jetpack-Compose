package com.ahmed.login_jetpack_compose.ui.presentation.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isPasswordVisible: Boolean = false
)

sealed class LoginEvent {
    data class NavigateToUserData(val userId: String) : LoginEvent()
}