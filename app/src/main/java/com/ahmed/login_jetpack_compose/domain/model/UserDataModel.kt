package com.ahmed.login_jetpack_compose.domain.model

data class UserDataModel(
    val id: Int = 0,
    val userId: String,
    val name: String,
    val occupation: String,
    val age: Int,
    val gender: String
)
