package com.ahmed.login_jetpack_compose.domain.usecase

import com.ahmed.login_jetpack_compose.domain.model.UserDataModel
import com.ahmed.login_jetpack_compose.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(userId: String): Flow<List<UserDataModel>> {
        return userDataRepository.getUserData(userId)
    }
}