package com.ahmed.login_jetpack_compose.data.mapper

import com.ahmed.login_jetpack_compose.data.local.entity.LoginEntity
import com.ahmed.login_jetpack_compose.data.local.entity.UserDataEntity
import com.ahmed.login_jetpack_compose.domain.model.LoginModel
import com.ahmed.login_jetpack_compose.domain.model.UserDataModel

//Login-------
fun LoginEntity.toModel() = LoginModel(
    userId = userId,
    email = email,
    password = password
)

fun LoginModel.toEntity() = LoginEntity(
    userId = userId,
    email = email,
    password = password
)

//User Data---------------------
fun UserDataEntity.toModel() = UserDataModel(
    id = id,
    userId = userId,
    name = name,
    occupation = occupation,
    age = age,
    gender = gender
)

fun UserDataModel.toEntity() = UserDataEntity(
    id = id,
    userId = userId,
    name = name,
    occupation = occupation,
    age = age,
    gender = gender
)