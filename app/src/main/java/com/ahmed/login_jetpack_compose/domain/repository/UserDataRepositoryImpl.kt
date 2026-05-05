package com.ahmed.login_jetpack_compose.domain.repository

import com.ahmed.login_jetpack_compose.data.local.dao.UserDataDao
import com.ahmed.login_jetpack_compose.data.mapper.toEntity
import com.ahmed.login_jetpack_compose.data.mapper.toModel
import com.ahmed.login_jetpack_compose.domain.model.UserDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val userDataDao: UserDataDao
) : UserDataRepository {

    override suspend fun addUserData(userDataModel: UserDataModel): Result<Long> {
        return try {
            val id = userDataDao.insertUserData(userDataModel.toEntity())
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getUserData(userId: String): Flow<List<UserDataModel>> {
        return userDataDao.getUserDataByUserId(userId).map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun deletedUserData(id: Int) {
        userDataDao.deleteUserDataById(id)
    }


}