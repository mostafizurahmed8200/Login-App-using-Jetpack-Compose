package com.ahmed.login_jetpack_compose.domain.usecase

import com.ahmed.login_jetpack_compose.domain.model.UserDataModel
import com.ahmed.login_jetpack_compose.domain.repository.UserDataRepository
import javax.inject.Inject

class AddUserDataUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(userDataModel: UserDataModel): Result<Long> {

        if (userDataModel.name.isBlank()) {
            return Result.failure(Exception("Name can't be empty"))
        }
        if (userDataModel.occupation.isBlank()) {
            return Result.failure(Exception("Occupation can't be empty"))
        }
        if (userDataModel.age !in 1..120) {
            return Result.failure(Exception("Please enter a valid age"))
        }

        if (userDataModel.gender.isBlank()) {
            return Result.failure(Exception("Please select a gender"))
        }

        return userDataRepository.addUserData(userDataModel)
    }

}