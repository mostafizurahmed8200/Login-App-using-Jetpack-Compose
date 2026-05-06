package com.ahmed.login_jetpack_compose.ui.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.login_jetpack_compose.domain.usecase.LoginUseCase
import com.ahmed.login_jetpack_compose.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase, private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _eventChannel = Channel<LoginEvent>()
    val events = _eventChannel.receiveAsFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }

    fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true, errorMessage = null
                )
            }
            loginUseCase.invoke(
                email = _uiState.value.email.trim(), password = _uiState.value.password.trim()
            ).fold(onSuccess = { loginModel ->
                _uiState.update { it.copy(isLoading = false) }
                _eventChannel.send(LoginEvent.NavigateToUserData(loginModel.userId))
            }, onFailure = { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false, errorMessage = throwable.message ?: "Login Failed"
                    )
                }

            })


        }
    }

    fun onRegisterClick() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true, errorMessage = null
                )
            }

            registerUseCase.invoke(
                email = _uiState.value.email.trim(), password = _uiState.value.password
            ).fold(onSuccess = { loginModel ->
                _uiState.update { it.copy(isLoading = false) }
                _eventChannel.send(LoginEvent.NavigateToUserData(loginModel.userId))
            }, onFailure = { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Registration Failed"
                    )
                }
            })
        }
    }


}