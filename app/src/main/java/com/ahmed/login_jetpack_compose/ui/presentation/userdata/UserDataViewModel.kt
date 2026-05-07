package com.ahmed.login_jetpack_compose.ui.presentation.userdata

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.login_jetpack_compose.domain.model.UserDataModel
import com.ahmed.login_jetpack_compose.domain.usecase.AddUserDataUseCase
import com.ahmed.login_jetpack_compose.domain.usecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    private val addUserDataUseCase: AddUserDataUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val userId: String = checkNotNull(savedStateHandle["userId"])

    private val _uiState = MutableStateFlow(UserDataUiState())
    val uiState: StateFlow<UserDataUiState> = _uiState.asStateFlow()

    init {
        loadUserData()
    }

    private fun loadUserData() {
        getUserDataUseCase.invoke(userId).onEach { list ->
            _uiState.update { it.copy(userDataList = list) }
        }.launchIn(viewModelScope)
    }

    fun onNameChange(name: String) {
        _uiState.update { it.copy(name = name, errorMessage = null) }
    }

    fun onOccupationChange(occupation: String) {
        _uiState.update { it.copy(occupation = occupation, errorMessage = null) }
    }

    fun onAgeChange(age: String) {
        _uiState.update { it.copy(age = age, errorMessage = null) }
    }

    fun onGenderChange(gender: String) {
        _uiState.update { it.copy(gender = gender, errorMessage = null) }
    }

    fun onAddUserData() {
        viewModelScope.launch {
            val age = _uiState.value.age.toIntOrNull() ?: 0
            val userDataModel = UserDataModel(
                userId = userId,
                name = _uiState.value.name.trim(),
                occupation = _uiState.value.occupation.trim(),
                age = age,
                gender = _uiState.value.gender
            )

            _uiState.update {
                it.copy(
                    isLoading = true, errorMessage = null
                )
            }
            addUserDataUseCase(userDataModel).fold(onSuccess = {
                _uiState.update {
                    it.copy(
                        isLoading = false, isAddSuccess = true,
                        name = "",
                        occupation = "",
                        age = "",
                        gender = ""
                    )
                }

            }, onFailure = { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Failed to add user data"
                    )
                }
            })


        }
    }


}