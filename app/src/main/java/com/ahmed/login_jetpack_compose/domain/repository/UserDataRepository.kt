package com.ahmed.login_jetpack_compose.domain.repository

import com.ahmed.login_jetpack_compose.domain.model.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    suspend fun addUserData(userDataModel: UserDataModel): Result<Long>
    fun getUserData(userId: String): Flow<List<UserDataModel>>
    suspend fun deletedUserData(id: Int)
}