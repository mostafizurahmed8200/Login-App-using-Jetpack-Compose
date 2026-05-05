package com.ahmed.login_jetpack_compose.ui.presentation.userdata

import com.ahmed.login_jetpack_compose.domain.model.UserDataModel

data class UserDataUiState
    (
    val userDataList: List<UserDataModel> = emptyList(),
    val name: String = "",
    val occupation: String = "",
    val age: String = "",
    val gender: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isAddSuccess: Boolean = false
)